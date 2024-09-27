package vn.edu.ptit.supermarket.core_authentication.service;

public interface AuthTokenService {

  /* ACCESS TOKEN */
  String generateAccessToken(String memberId, String email, String username);
  String getSubjectFromAccessToken(String accessToken);
  boolean validateAccessToken(String accessToken, String memberId);

  /* REFRESH TOKEN */
  String generateRefreshToken(String memberId, String email, String username);
  String getSubjectFromRefreshToken(String refreshToken);
  boolean validateRefreshToken(String refreshToken, String memberId);

  long getAccessTokenLifeTime();
  long getRefreshTokenLifeTime();
}
