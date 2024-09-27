package vn.edu.ptit.supermarket.core_authentication.model.response;

import lombok.Data;

@Data
public class ActivityLoginResponse extends LoginResponse{

  private String accessToken;
  private String refreshToken;
  private long accessTokenLifeTime;
  private long refreshTokenLifeTime;
}
