package vn.edu.ptit.supermarket.core_authentication.exception;

import vn.edu.ptit.supermarket.core_exception.exception.ConflictException;

public class AccountExistedException extends ConflictException {

  public AccountExistedException(String accountId) {
    setStatus(409);
    setCode("AccountExistedException");
    addParams("accountId", accountId);
  }
}
