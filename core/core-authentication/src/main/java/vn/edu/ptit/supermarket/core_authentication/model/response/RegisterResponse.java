package vn.edu.ptit.supermarket.core_authentication.model.response;

import lombok.Data;

@Data
public class RegisterResponse {

  public static final String UNREGISTERED = "UNREGISTERED";
  public static final String ACTIVE = "ACTIVE";
  public static final String INACTIVE = "INACTIVE";
}
