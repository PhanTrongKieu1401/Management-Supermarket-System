package vn.edu.ptit.supermarket.core_authentication.service;

import io.jsonwebtoken.Claims;

public interface AuthTokenService {

  /* ACCESS TOKEN */
  Claims getClaimsFromAccessToken(String token);
  String generateAccessToken(String memberId, String email, String role, String username);
  String getSubjectFromAccessToken(String accessToken);
  boolean validateAccessToken(String accessToken, String memberId);

  /* REFRESH TOKEN */
  String generateRefreshToken(String memberId, String email, String role, String username);
  String getSubjectFromRefreshToken(String refreshToken);
  boolean validateRefreshToken(String refreshToken, String memberId);

  long getAccessTokenLifeTime();
  long getRefreshTokenLifeTime();
}
