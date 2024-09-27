package vn.edu.ptit.supermarket.core_authentication.exception;

import vn.edu.ptit.supermarket.core_exception.exception.BadRequestException;

public class OtpInvalidException extends BadRequestException {

  public OtpInvalidException(String otp) {
    setStatus(400);
    setCode("OtpInvalidException");
    addParams("otp", otp);
  }
}
