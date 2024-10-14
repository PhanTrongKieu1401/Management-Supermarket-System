package vn.edu.ptit.supermarket.core_authentication.facade.impl;

import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.ptit.supermarket.core_authentication.constant.AccountLockedTime;
import vn.edu.ptit.supermarket.core_authentication.constant.ResendOtpType;
import vn.edu.ptit.supermarket.core_authentication.entity.Account;
import vn.edu.ptit.supermarket.core_authentication.entity.Member;
import vn.edu.ptit.supermarket.core_authentication.exception.AccountLockedException;
import vn.edu.ptit.supermarket.core_authentication.exception.AccountLockedTemporaryException;
import vn.edu.ptit.supermarket.core_authentication.exception.AccountNotActivatedException;
import vn.edu.ptit.supermarket.core_authentication.exception.EmailNotFoundException;
import vn.edu.ptit.supermarket.core_authentication.exception.OtpInvalidException;
import vn.edu.ptit.supermarket.core_authentication.exception.OtpNotFoundException;
import vn.edu.ptit.supermarket.core_authentication.exception.OtpStillValidException;
import vn.edu.ptit.supermarket.core_authentication.exception.PasswordConfirmNotMatchException;
import vn.edu.ptit.supermarket.core_authentication.exception.PasswordIncorrectException;
import vn.edu.ptit.supermarket.core_authentication.exception.RegisterKeyNotFoundException;
import vn.edu.ptit.supermarket.core_authentication.exception.ResetPasswordKeyNotFoundException;
import vn.edu.ptit.supermarket.core_authentication.exception.TypeResendInvalidException;
import vn.edu.ptit.supermarket.core_authentication.exception.UsernameNotFoundException;
import vn.edu.ptit.supermarket.core_authentication.facade.AuthFacadeService;
import vn.edu.ptit.supermarket.core_authentication.model.request.LoginRequest;
import vn.edu.ptit.supermarket.core_authentication.model.request.RegisterRequest;
import vn.edu.ptit.supermarket.core_authentication.model.request.ResendOtpRequest;
import vn.edu.ptit.supermarket.core_authentication.model.request.ResetPasswordRequest;
import vn.edu.ptit.supermarket.core_authentication.model.request.VerifyEmailRequest;
import vn.edu.ptit.supermarket.core_authentication.model.request.VerifyOtpRequest;
import vn.edu.ptit.supermarket.core_authentication.model.response.ActivityLoginResponse;
import vn.edu.ptit.supermarket.core_authentication.model.response.LoginResponse;
import vn.edu.ptit.supermarket.core_authentication.model.response.RegisterResponse;
import vn.edu.ptit.supermarket.core_authentication.model.response.VerifyRegisterResponse;
import vn.edu.ptit.supermarket.core_authentication.model.response.VerifyResetPasswordResponse;
import vn.edu.ptit.supermarket.core_authentication.service.AccountService;
import vn.edu.ptit.supermarket.core_authentication.service.AddressService;
import vn.edu.ptit.supermarket.core_authentication.service.AuthTokenService;
import vn.edu.ptit.supermarket.core_authentication.service.MemberService;
import vn.edu.ptit.supermarket.core_authentication.service.OtpService;
import vn.edu.ptit.supermarket.core_authentication.util.CryptUtil;
import vn.edu.ptit.supermarket.core_email.healper.EmailHelper;
import vn.edu.ptit.supermarket.core_redis.constant.CacheConstant;
import vn.edu.ptit.supermarket.core_redis.service.RedisService;

@Slf4j
@RequiredArgsConstructor
public class AuthFacadeServiceImpl implements AuthFacadeService {

  private final AccountService accountService;
  private final MemberService memberService;
  private final AddressService addressService;
  private final AuthTokenService authTokenService;
  private final OtpService otpService;
  private final RedisService redisService;
  private final EmailHelper emailHelper;

  @Override
  public LoginResponse login(LoginRequest loginRequest) {
    log.info("(login)loginRequest: {}", loginRequest);
    Account account = accountService.findByUsername(loginRequest.getUsername());
    if (account == null) {
      log.error("(login)loginRequest: {} not found", loginRequest.getUsername());
      throw new UsernameNotFoundException(loginRequest.getUsername());
    }

    if (account.getIsLockedPermanent()) {
      log.error("(login)loginRequest: {} is locked", loginRequest.getUsername());
      throw new AccountLockedException();
    }

    if (!account.getIsActivated()) {
      log.error("(login)loginRequest: {} didn't activated", loginRequest.getUsername());
      throw new AccountNotActivatedException();
    }

    Member member = memberService.findByAccountId(account.getId());

    Long unLockLoginTime = redisService.getOrDefault(CacheConstant.UNLOCK_LOGIN_TIME_KEY,
        member.getId(), 0L);
    if (unLockLoginTime > 0 && unLockLoginTime > System.currentTimeMillis()) {
      log.error("(login)loginRequest: {} is locked temporary", loginRequest.getUsername());
      throw new AccountLockedTemporaryException();
    }

    if (!CryptUtil.getPasswordEncoder()
        .matches(loginRequest.getPassword(), account.getPassword())) {
      handleFailedAttempt(member.getEmail(), account.getId());
      log.error("(invoke)account has username : {} is have wrong password", account.getUsername());
      throw new PasswordIncorrectException();
    }

    redisService.delete(CacheConstant.UNLOCK_LOGIN_TIME_KEY, member.getEmail());
    redisService.delete(CacheConstant.LOGIN_FAILED_ATTEMPT_KEY, member.getEmail());

    return createLoginResponse(member, account);
  }

  @Override
  @Transactional
  public void register(RegisterRequest registerRequest) {
    log.info("(register)registerRequest: {}", registerRequest);

    if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
      log.error("(register)registerRequest: {} and {} not match", registerRequest.getPassword(),
          registerRequest.getConfirmPassword());
      throw new PasswordConfirmNotMatchException();
    }

    var registerKey = redisService.get(CacheConstant.REGISTER_KEY, registerRequest.getEmail());
    if (registerKey.isEmpty() || !registerKey.get().equals(registerRequest.getRegisterKey())) {
      log.error("(register)registerRequest not found for email {}", registerRequest.getEmail());
      throw new RegisterKeyNotFoundException(registerRequest.getEmail());
    }

    var account = accountService.create(registerRequest.getUsername(),
        CryptUtil.getPasswordEncoder()
            .encode(registerRequest.getPassword()));

//    var address = addressService.create(
//        Address.of(registerRequest.getAddressDetail(), registerRequest.getWard(),
//            registerRequest.getDistrict(), registerRequest.getProvince()));

//    var member = memberService.create(registerRequest, account.getId(), address.getId());
    var member = memberService.create(registerRequest, account, null);

    redisService.delete(CacheConstant.REGISTER_KEY, registerRequest.getEmail());
  }

  @Override
  public VerifyRegisterResponse verifyRegister(VerifyOtpRequest verifyOtpRequest) {
    log.info("(verifyRegister)email: {} otp: {}", verifyOtpRequest.getEmail(),
        verifyOtpRequest.getOtp());
    var redisKey = verifyOtpRequest.getEmail() + CacheConstant.OTP_VERIFICATION_ACCOUNT_KEY;
    var otpCacheOptional = redisService.get(redisKey);
    if (otpCacheOptional.isEmpty()) {
      log.error("(verifyRegister)otpCache is null for email: {}", verifyOtpRequest.getEmail());
      throw new OtpNotFoundException(verifyOtpRequest.getEmail());
    }
    var otpCache = String.valueOf(otpCacheOptional.get());
    if (!Objects.equals(otpCache, verifyOtpRequest.getOtp())) {
      log.error("(verifyRegister)otp : {}, otpCache : {}", verifyOtpRequest.getOtp(), otpCache);
      throw new OtpInvalidException(verifyOtpRequest.getOtp());
    }
    redisService.delete(redisKey);

    var registerRedisKey = generateResetPasswordKey(verifyOtpRequest.getEmail());
    redisService.save(CacheConstant.REGISTER_KEY, verifyOtpRequest.getEmail(), registerRedisKey);

    return VerifyRegisterResponse.builder()
        .registerKey(registerRedisKey)
        .build();
  }

  @Override
  public String verifyEmail(VerifyEmailRequest verifyEmailRequest) {
    log.info("(verifyEmail)email: {}", verifyEmailRequest.getEmail());
    if (!memberService.existsByEmail(verifyEmailRequest.getEmail())) {
      log.info("(verifyEmail)email don't registered: {}", verifyEmailRequest.getEmail());

      var redisKey = verifyEmailRequest.getEmail() + CacheConstant.OTP_VERIFICATION_ACCOUNT_KEY;
      var otpCache = redisService.get(redisKey);
      if (otpCache.isPresent()) {
        log.error("(verifyEmail)otpCache is still valid for email: {}",
            verifyEmailRequest.getEmail());
        throw new OtpStillValidException(verifyEmailRequest.getEmail());
      }
      var otp = otpService.generateOtp();
      redisService.save(redisKey, otp, CacheConstant.OTP_TTL_MINUTES, TimeUnit.MINUTES);

      String subject = "Your OTP for account verification";
      var param = new HashMap<String, Object>();
      param.put("otp", otp);
      param.put("otp_life", String.valueOf(CacheConstant.OTP_TTL_MINUTES));
      emailHelper.send(subject, verifyEmailRequest.getEmail(), "OTP-template", param);
      return RegisterResponse.UNREGISTERED;
    } else {
      log.info("(verifyEmail)email has been registered: {}", verifyEmailRequest.getEmail());
      return RegisterResponse.ACTIVE;
    }
  }

  @Override
  public void forgotPassword(VerifyEmailRequest verifyEmailRequest) {
    log.info("(forgotPassword)email: {}", verifyEmailRequest.getEmail());
    if (!memberService.existsByEmail(verifyEmailRequest.getEmail())) {
      log.error("(forgotPassword)email: {}", verifyEmailRequest.getEmail());
      throw new EmailNotFoundException(verifyEmailRequest.getEmail());
    }
    var redisKey = verifyEmailRequest.getEmail() + CacheConstant.RESET_PASSWORD_OTP_KEY;
    var otpCache = redisService.get(redisKey);
    if (otpCache.isPresent()) {
      log.error("(forgotPassword)otpCache is still valid for email: {}",
          verifyEmailRequest.getEmail());
      throw new OtpStillValidException(verifyEmailRequest.getEmail());
    }
    var otp = otpService.generateOtp();
    redisService.save(redisKey, otp, CacheConstant.OTP_TTL_MINUTES, TimeUnit.MINUTES);

    String subject = "Your OTP for rest password";
    var param = new HashMap<String, Object>();
    param.put("otp", otp);
    param.put("otp_life", String.valueOf(CacheConstant.OTP_TTL_MINUTES));
    emailHelper.send(subject, verifyEmailRequest.getEmail(), "OTP-template", param);
  }

  @Override
  public VerifyResetPasswordResponse verifyResetPassword(VerifyOtpRequest verifyOtpRequest) {
    log.info("(verifyResetPassword)email: {} otp: {}", verifyOtpRequest.getEmail(),
        verifyOtpRequest.getOtp());
    if (!memberService.existsByEmail(verifyOtpRequest.getEmail())) {
      log.error("(verifyResetPassword)email: {}", verifyOtpRequest.getEmail());
      throw new EmailNotFoundException(verifyOtpRequest.getEmail());
    }

    var redisKey = verifyOtpRequest.getEmail() + CacheConstant.RESET_PASSWORD_OTP_KEY;
    var cachedOtp = redisService.get(redisKey);
    log.error("(verifyResetPassword)otpCache: {}", cachedOtp);

    if (cachedOtp.isEmpty() || !cachedOtp.get().equals(verifyOtpRequest.getOtp())) {
      log.error("(verifyResetPassword) OTP not found for email: {}", verifyOtpRequest.getEmail());
      throw new OtpNotFoundException(verifyOtpRequest.getEmail());
    }

    log.info("(verifyResetPassword) OTP validated successfully for email: {}",
        verifyOtpRequest.getEmail());
    redisService.delete(redisKey);

    var resetPasswordKeyRedisKey = generateResetPasswordKey(verifyOtpRequest.getEmail());
    redisService.save(CacheConstant.RESET_PASSWORD_KEY, verifyOtpRequest.getEmail(),
        resetPasswordKeyRedisKey);

    return VerifyResetPasswordResponse.builder()
        .resetPasswordKey(resetPasswordKeyRedisKey)
        .build();
  }

  @Override
  public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
    log.info("(resetPassword)email: {}, password: {}, confirmPassword: {}",
        resetPasswordRequest.getEmail(), resetPasswordRequest.getPassword(),
        resetPasswordRequest.getConfirmPassword());

    if (!resetPasswordRequest.getPassword().equals(resetPasswordRequest.getConfirmPassword())) {
      log.error("(resetPassword) Password and confirmation password do not match: {} {}",
          resetPasswordRequest.getPassword(),
          resetPasswordRequest.getConfirmPassword());
      throw new PasswordConfirmNotMatchException();
    }

    if (!memberService.existsByEmail(resetPasswordRequest.getEmail())) {
      log.error("(resetPassword)email: {}", resetPasswordRequest.getEmail());
      throw new EmailNotFoundException(resetPasswordRequest.getEmail());
    }

    var resetPasswordKey = redisService.get(CacheConstant.RESET_PASSWORD_KEY,
        resetPasswordRequest.getEmail());
    if (resetPasswordKey.isEmpty() ||
        !resetPasswordKey.get().equals(resetPasswordRequest.getResetPasswordKey())) {
      log.error("(resetPassword) Reset Password Key not found: {}",
          resetPasswordRequest.getResetPasswordKey());
      throw new ResetPasswordKeyNotFoundException();
    }

    redisService.delete(CacheConstant.RESET_PASSWORD_KEY, resetPasswordRequest.getEmail());

    var account = accountService.findByEmail(resetPasswordRequest.getEmail());
    if (account == null) {
      log.error("(resetPassword)email not found : {}", resetPasswordRequest.getEmail());
      throw new EmailNotFoundException(resetPasswordRequest.getEmail());
    }

    account.setPassword(CryptUtil.getPasswordEncoder().encode(resetPasswordRequest.getPassword()));

    accountService.save(account);
  }

  @Override
  public void resendOtp(ResendOtpRequest resendOtpRequest) {
    log.info("(resendOtp)email: {}", resendOtpRequest.getEmail());

    if (!memberService.existsByEmail(resendOtpRequest.getEmail())) {
      log.error("(resendOtp)email: {}", resendOtpRequest.getEmail());
      throw new EmailNotFoundException(resendOtpRequest.getEmail());
    }

    String type = resendOtpRequest.getType().trim().toUpperCase();

    if (type.equals(ResendOtpType.FORGOT.toString())) {
      log.info("(resendOtp) come forgot");
      resend(resendOtpRequest.getEmail(), CacheConstant.RESET_PASSWORD_OTP_KEY);
    } else if (type.equals(ResendOtpType.REGISTER.toString())) {
      log.info("(resendOtp) come register");
      resend(resendOtpRequest.getEmail(), CacheConstant.REGISTER_KEY);
    } else {
      log.error("(resendOtp) Invalid resend type {} value", resendOtpRequest.getType());
      throw new TypeResendInvalidException(resendOtpRequest.getType());
    }
  }

  private void resend(String email, String otpKey) {
    log.info("(resend)email {}, otpKey {}", email, otpKey);
    var redisKey = email + otpKey;

    var otpCache = redisService.get(redisKey);
    if (otpCache.isPresent()) {
      log.error("(resend)otpCache is still valid for email: {}", email);
      throw new OtpStillValidException(email);
    }

    var otp = otpService.generateOtp();
    redisService.save(redisKey, otp, CacheConstant.OTP_TTL_MINUTES, TimeUnit.MINUTES);

    String subject = "Your OTP for password reset";
    var param = new HashMap<String, Object>();
    param.put("otp", otp);
    param.put("otp_life", String.valueOf(CacheConstant.OTP_TTL_MINUTES));
    emailHelper.send(subject, email, "OTP-template", param);
  }

  private void handleFailedAttempt(String email, String accountId) {
    log.info("(handleFailedAttempt) email: {}", email);
    Object attempt = redisService.getOrDefault(CacheConstant.LOGIN_FAILED_ATTEMPT_KEY, email, 0);
    Integer attempts = Integer.parseInt(attempt.toString());
    attempts++;
    redisService.save(CacheConstant.LOGIN_FAILED_ATTEMPT_KEY, email, attempts);

    if (attempts.equals(AccountLockedTime.FIVE.getAttempts())) {
      redisService.save(CacheConstant.UNLOCK_LOGIN_TIME_KEY,
          email, Instant.now().getEpochSecond() + AccountLockedTime.FIVE.getCooldownTime());
      log.info("Account locked for 5 failed attempts for email: {}", email);
    }
    if (attempts.equals(AccountLockedTime.TEN.getAttempts())) {
      redisService.save(CacheConstant.UNLOCK_LOGIN_TIME_KEY,
          email, Instant.now().getEpochSecond() + AccountLockedTime.TEN.getCooldownTime());
      log.info("Account locked for 10 failed attempts for email: {}", email);
    }
    if (attempts.equals(AccountLockedTime.FIFTEEN.getAttempts())) {
      accountService.updateLockAccount(accountId, true);
      redisService.delete(CacheConstant.UNLOCK_LOGIN_TIME_KEY, email);
      redisService.delete(CacheConstant.LOGIN_FAILED_ATTEMPT_KEY, email);
      log.info("Account locked for 15 failed attempts for email: {}", email);
    }
    log.info("(handleFailedAttempt)Customer have email : {} have {} failed attempts", email,
        attempts);
  }

  private LoginResponse createLoginResponse(Member member, Account account) {
    ActivityLoginResponse loginResponse = new ActivityLoginResponse();
    loginResponse.setAccessToken(
        authTokenService.generateAccessToken(member.getId(), member.getEmail(),
            member.getRole(), account.getUsername()));
    loginResponse.setRefreshToken(
        authTokenService.generateRefreshToken(member.getId(), member.getEmail(),
            member.getRole(), account.getUsername()));
    loginResponse.setAccessTokenLifeTime(authTokenService.getAccessTokenLifeTime());
    loginResponse.setRefreshTokenLifeTime(authTokenService.getRefreshTokenLifeTime());
    return loginResponse;
  }

  private String generateResetPasswordKey(String email) {
    return Base64.getEncoder().encodeToString((email + System.currentTimeMillis()).getBytes());
  }
}
