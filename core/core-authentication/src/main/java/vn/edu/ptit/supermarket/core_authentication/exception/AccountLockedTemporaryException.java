package vn.edu.ptit.supermarket.core_authentication.exception;

import vn.edu.ptit.supermarket.core_exception.exception.BadRequestException;

public class AccountLockedTemporaryException extends BadRequestException {

  public AccountLockedTemporaryException() {
    setStatus(400);
    setCode("AccountLockedTemporaryException");
  }
}
