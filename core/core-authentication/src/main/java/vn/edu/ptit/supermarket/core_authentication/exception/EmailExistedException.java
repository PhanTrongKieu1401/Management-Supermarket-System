package vn.edu.ptit.supermarket.core_authentication.exception;

import vn.edu.ptit.supermarket.core_exception.exception.ConflictException;

public class EmailExistedException extends ConflictException {

  public EmailExistedException(String email) {
    setStatus(409);
    setCode("EmailExistedException");
    addParams("email", email);
  }
}
