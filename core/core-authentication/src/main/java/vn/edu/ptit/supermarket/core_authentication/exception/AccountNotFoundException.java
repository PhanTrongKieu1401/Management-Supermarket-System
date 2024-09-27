package vn.edu.ptit.supermarket.core_authentication.exception;

import vn.edu.ptit.supermarket.core_exception.exception.NotFoundException;

public class AccountNotFoundException extends NotFoundException {

  public AccountNotFoundException(String accountId) {
    setStatus(404);
    setCode("AccountNotFoundException");
    addParams("accountId", accountId);
  }
}
