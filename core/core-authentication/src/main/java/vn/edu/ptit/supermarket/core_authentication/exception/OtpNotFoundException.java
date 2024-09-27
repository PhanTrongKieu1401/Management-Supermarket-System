package vn.edu.ptit.supermarket.core_authentication.exception;

import vn.edu.ptit.supermarket.core_exception.exception.NotFoundException;

public class OtpNotFoundException extends NotFoundException {

  public OtpNotFoundException(String email) {
    setStatus(404);
    setCode("OtpNotFoundException");
    addParams("email", email);
  }
}
