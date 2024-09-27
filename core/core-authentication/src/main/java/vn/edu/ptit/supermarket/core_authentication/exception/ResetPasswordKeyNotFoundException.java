package vn.edu.ptit.supermarket.core_authentication.exception;

import vn.edu.ptit.supermarket.core_exception.exception.NotFoundException;

public class ResetPasswordKeyNotFoundException extends NotFoundException {

  public ResetPasswordKeyNotFoundException() {
    setStatus(404);
    setCode("ResetPasswordKeyNotFoundException");
  }
}
