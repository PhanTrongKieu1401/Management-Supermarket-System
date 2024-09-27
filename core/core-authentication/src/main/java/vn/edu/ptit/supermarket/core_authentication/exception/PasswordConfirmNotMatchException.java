package vn.edu.ptit.supermarket.core_authentication.exception;

import vn.edu.ptit.supermarket.core_exception.exception.BadRequestException;

public class PasswordConfirmNotMatchException extends BadRequestException {

  public PasswordConfirmNotMatchException() {
    setStatus(400);
    setCode("PasswordConfirmNotMatchException");
  }
}
