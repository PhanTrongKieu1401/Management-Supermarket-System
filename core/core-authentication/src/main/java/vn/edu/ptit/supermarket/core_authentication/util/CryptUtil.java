package vn.edu.ptit.supermarket.core_authentication.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CryptUtil {

  private static final BCryptPasswordEncoder B_CRYPT_PASSWORD_ENCODER = new BCryptPasswordEncoder(16);

  public static PasswordEncoder getPasswordEncoder() {
    return B_CRYPT_PASSWORD_ENCODER;
  }
}
