package vn.edu.ptit.supermarket.core_authentication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.supermarket.core_authentication.facade.AuthFacadeService;
import vn.edu.ptit.supermarket.core_authentication.model.request.LoginRequest;
import vn.edu.ptit.supermarket.core_authentication.model.request.RegisterRequest;
import vn.edu.ptit.supermarket.core_authentication.model.request.ResendOtpRequest;
import vn.edu.ptit.supermarket.core_authentication.model.request.ResetPasswordRequest;
import vn.edu.ptit.supermarket.core_authentication.model.request.VerifyEmailRequest;
import vn.edu.ptit.supermarket.core_authentication.model.request.VerifyOtpRequest;
import vn.edu.ptit.supermarket.core_authentication.model.response.BaseResponse;
import vn.edu.ptit.supermarket.core_authentication.model.response.LoginResponse;
import vn.edu.ptit.supermarket.core_authentication.model.response.VerifyRegisterResponse;
import vn.edu.ptit.supermarket.core_authentication.model.response.VerifyResetPasswordResponse;

@Slf4j
@RestController
@RequestMapping("/api/v1/public/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication API")
public class AuthAccountController {

  private final AuthFacadeService authFacadeService;

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Login", description = "Login API")
  public BaseResponse<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
    log.info("(login)loginRequest: {}", loginRequest);
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(),
        authFacadeService.login(loginRequest));
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Register", description = "Register API")
  public BaseResponse<String> register(@Valid @RequestBody RegisterRequest registerRequest) {
    log.info("(register)registerRequest: {}", registerRequest);
    authFacadeService.register(registerRequest);
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(),
        "Register successfully!");
  }

  @PostMapping("/register/otp/validate")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Verify register otp", description = "Verify register otp")
  public BaseResponse<VerifyRegisterResponse> verifyRegister(
      @Valid @RequestBody VerifyOtpRequest verifyOtpRequest) {
    log.info("(verifyRegister)email: {}, otp: {}", verifyOtpRequest.getEmail(),
        verifyOtpRequest.getOtp());
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(),
        authFacadeService.verifyRegister(verifyOtpRequest));
  }

  @PostMapping("/register/email/validate")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Verify email", description = "Verify email")
  public BaseResponse<String> verifyEmail(
      @RequestBody @Valid VerifyEmailRequest verifyEmailRequest) {
    log.info("(verifyEmail)email: {}", verifyEmailRequest.getEmail());
    return BaseResponse.of(HttpStatus.CREATED.value(), LocalDateTime.now().toString(),
        authFacadeService.verifyEmail(verifyEmailRequest));
  }

  @PostMapping("/forgot")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Forgot password", description = "Forgot password")
  public BaseResponse<String> forgotPassword(
      @RequestBody @Valid VerifyEmailRequest verifyEmailRequest) {
    log.info("(forgotPassword)email: {}", verifyEmailRequest.getEmail());
    authFacadeService.forgotPassword(verifyEmailRequest);
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(),
        "An OTP for password reset has been sent to your email address. " +
            "Please check your inbox to proceed with resetting your password.");
  }

  @PostMapping("/reset-password/otp/validate")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Verify reset password", description = "Verify reset password")
  public BaseResponse<VerifyResetPasswordResponse> verifyResetPassword(
      @RequestBody @Valid VerifyOtpRequest verifyOtpRequest) {
    log.info("(verifyResetPassword)email: {}, otp: {}", verifyOtpRequest.getEmail(),
        verifyOtpRequest.getOtp());
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(),
        authFacadeService.verifyResetPassword(verifyOtpRequest));
  }

  @PostMapping("/reset-password")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Reset password", description = "Reset password")
  public BaseResponse<String> resetPassword(
      @RequestBody @Valid ResetPasswordRequest resetPasswordRequest) {
    log.info("(resetPassword)email: {}, password: {}, confirmPassword: {}",
        resetPasswordRequest.getEmail(), resetPasswordRequest.getPassword(),
        resetPasswordRequest.getConfirmPassword());
    authFacadeService.resetPassword(resetPasswordRequest);
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(),
        "Reset Password successfully!");
  }

  @PostMapping("/resend/otp")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Resend otp", description = "Resend otp")
  public BaseResponse<String> resendOtp(@RequestBody @Valid ResendOtpRequest resendOtpRequest) {
    log.info("(resendOtp)email: {}, type: {}", resendOtpRequest.getEmail(), resendOtpRequest.getType());
    authFacadeService.resendOtp(resendOtpRequest);
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(),
        "Resend Otp successfully!");
  }
}
