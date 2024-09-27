package vn.edu.ptit.supermarket.core_authentication.exception;

import vn.edu.ptit.supermarket.core_exception.exception.NotFoundException;

public class EmailNotFoundException extends NotFoundException {

  public EmailNotFoundException(String email) {
    setStatus(404);
    setCode("EmailNotFoundException");
    addParams("email", email);
  }
}
