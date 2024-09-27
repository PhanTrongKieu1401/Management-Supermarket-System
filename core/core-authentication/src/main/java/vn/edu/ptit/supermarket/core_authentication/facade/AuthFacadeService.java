package vn.edu.ptit.supermarket.core_authentication.facade;

import javax.security.auth.login.AccountNotFoundException;
import vn.edu.ptit.supermarket.core_authentication.model.request.LoginRequest;
import vn.edu.ptit.supermarket.core_authentication.model.request.RegisterRequest;
import vn.edu.ptit.supermarket.core_authentication.model.request.ResendOtpRequest;
import vn.edu.ptit.supermarket.core_authentication.model.request.ResetPasswordRequest;
import vn.edu.ptit.supermarket.core_authentication.model.request.VerifyEmailRequest;
import vn.edu.ptit.supermarket.core_authentication.model.request.VerifyOtpRequest;
import vn.edu.ptit.supermarket.core_authentication.model.response.LoginResponse;
import vn.edu.ptit.supermarket.core_authentication.model.response.VerifyRegisterResponse;
import vn.edu.ptit.supermarket.core_authentication.model.response.VerifyResetPasswordResponse;

public interface AuthFacadeService {

  LoginResponse login(LoginRequest loginRequest);
  void register(RegisterRequest registerRequest);
  VerifyRegisterResponse verifyRegister(VerifyOtpRequest verifyOtpRequest);
  String verifyEmail(VerifyEmailRequest verifyEmailRequest);
  void forgotPassword(VerifyEmailRequest verifyEmailRequest);
  VerifyResetPasswordResponse verifyResetPassword(VerifyOtpRequest verifyOtpRequest);
  void resetPassword(ResetPasswordRequest resetPasswordRequest);
  void resendOtp(ResendOtpRequest resendOtpRequest);
}
