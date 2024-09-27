package vn.edu.ptit.supermarket.core_authentication.exception;

import vn.edu.ptit.supermarket.core_exception.exception.BadRequestException;

public class AccountLockedException extends BadRequestException {

  public AccountLockedException() {
    setStatus(400);
    setCode("AccountLockedException");
  }
}
