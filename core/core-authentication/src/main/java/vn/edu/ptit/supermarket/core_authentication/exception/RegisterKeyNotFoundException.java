package vn.edu.ptit.supermarket.core_authentication.exception;

import vn.edu.ptit.supermarket.core_exception.exception.BadRequestException;

public class RegisterKeyNotFoundException extends BadRequestException {

  public RegisterKeyNotFoundException(String email) {
    setStatus(400);
    setCode("RegisterKeyNotFoundException");
    addParams("email", email);
  }
}
