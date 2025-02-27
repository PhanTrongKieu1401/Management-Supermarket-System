package vn.edu.ptit.supermarket.core_authentication.service.impl;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.ptit.supermarket.core_authentication.service.OtpService;

@Slf4j
@Service
public class OtpServiceImpl implements OtpService {

  private static final int OTP_LENGTH = 6;

  @Override
  public String generateOtp() {
    StringBuilder otpBuilder = new StringBuilder(OTP_LENGTH);
    Random random = new Random();
    for (int i = 0; i < OTP_LENGTH; i++) {
      otpBuilder.append(random.nextInt(10));
    }
    var otp = otpBuilder.toString();
    log.debug("(generate): {}", otp);
    return otp;
  }
}
