package vn.edu.ptit.supermarket.core_authentication.exception;

import vn.edu.ptit.supermarket.core_exception.exception.NotFoundException;

public class UsernameNotFoundException extends NotFoundException {

  public UsernameNotFoundException(String username) {
    setStatus(404);
    setCode("UsernameNotFoundException");
    addParams("username", username);
  }
}
