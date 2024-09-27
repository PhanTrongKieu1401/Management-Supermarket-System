package vn.edu.ptit.supermarket.core_authentication.exception;

import vn.edu.ptit.supermarket.core_exception.exception.BadRequestException;

public class AccountNotActivatedException extends BadRequestException {

  public AccountNotActivatedException() {
    setStatus(400);
    setCode("AccountNotActivatedException");
  }
}
