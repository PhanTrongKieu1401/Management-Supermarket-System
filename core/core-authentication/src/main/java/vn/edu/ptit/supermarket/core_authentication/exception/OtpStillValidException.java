package vn.edu.ptit.supermarket.core_authentication.exception;

import vn.edu.ptit.supermarket.core_exception.exception.BadRequestException;

public class OtpStillValidException extends BadRequestException {

  public OtpStillValidException(String email) {
    setStatus(400);
    setCode("OtpStillValidException");
    addParams("email", email);
  }
}
