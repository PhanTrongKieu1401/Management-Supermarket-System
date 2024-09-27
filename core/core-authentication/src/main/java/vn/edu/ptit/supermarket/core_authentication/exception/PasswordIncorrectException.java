package vn.edu.ptit.supermarket.core_authentication.exception;

import vn.edu.ptit.supermarket.core_exception.exception.BadRequestException;

public class PasswordIncorrectException extends BadRequestException {

  public PasswordIncorrectException() {
    setStatus(400);
    setCode("PasswordIncorrectException");
  }
}
