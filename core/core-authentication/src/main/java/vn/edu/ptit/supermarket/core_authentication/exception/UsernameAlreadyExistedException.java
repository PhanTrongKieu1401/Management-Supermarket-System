package vn.edu.ptit.supermarket.core_authentication.exception;

import vn.edu.ptit.supermarket.core_exception.exception.ConflictException;

public class UsernameAlreadyExistedException extends ConflictException {
  public UsernameAlreadyExistedException(String username) {
    setStatus(409);
    setCode("UsernameAlreadyExistedException");
    addParams("username", username);
  }

}
