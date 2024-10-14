package vn.edu.ptit.supermarket.core_web_socket.modal;

import java.security.Principal;

public class TokenPrincipal implements Principal {

  private final String token;

  public TokenPrincipal(String token) {
    this.token = token;
  }

  @Override
  public String getName() {
    return "";
  }
}
