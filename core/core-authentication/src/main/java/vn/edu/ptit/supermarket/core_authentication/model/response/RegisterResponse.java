package vn.edu.ptit.supermarket.core_authentication.model.response;

import lombok.Data;

@Data
public class RegisterResponse {

  public static final String UNREGISTERED = "SUCCESS";
  public static final String ACTIVE = "EXISTED";
  public static final String INACTIVE = "ERROR";
}
