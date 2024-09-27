package vn.edu.ptit.supermarket.core_authentication.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vn.edu.ptit.supermarket.core_authentication.service.AuthTokenService;

@Slf4j
@Component
public class AuthTokenServiceImpl implements AuthTokenService {

  @Value("${application.authentication.access_token.jwt_secret:N4ND53ndsU}")
  private String accessTokenJwtSecret;

  @Value("${application.authentication.access_token.life_time:36000000}")
  private Long accessTokenLifeTime;

  @Value("${application.authentication.refresh_token.jwt_secret:N4ND53ndsU}")
  private String refreshTokenJwtSecret;

  @Value("${application.authentication.refresh_token.life_time:72000000}")
  private Long refreshTokenLifeTime;

  private String generateToken(String subject, Map<String, Object> claims, long tokenLifeTime, String jwtSecret) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + tokenLifeTime))
        .signWith(SignatureAlgorithm.HS256, jwtSecret)
        .compact();
  }

  private boolean isActiveToken(String token, String secretKey) {
    return getClaim(token, Claims::getExpiration, secretKey).after(new Date());
  }

  private <T> T getClaim(String token, Function<Claims, T> claimsResolve, String secretKey) {
    return claimsResolve.apply(getClaims(token, secretKey));
  }

  private Claims getClaims(String token, String secretKey) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
  }

  @Override
  public String generateAccessToken(String memberId, String email, String username) {
    log.info("(generateAccessToken)memberId: {}, email: {}, username: {}", memberId, email, username);
    var claims = new HashMap<String, Object>();
    claims.put("email", email);
    claims.put("username", username);
    return generateToken(memberId, claims, accessTokenLifeTime, accessTokenJwtSecret);
  }

  @Override
  public String getSubjectFromAccessToken(String accessToken) {
    log.debug("(getSubjectFromAccessToken)accessToken: {}", accessToken);
    return getClaim(accessToken, Claims::getSubject, accessTokenJwtSecret);
  }

  @Override
  public boolean validateAccessToken(String accessToken, String memberId) {
    log.info("(validateAccessToken)accessToken: {}, memberId: {}", accessToken, memberId);
    return getSubjectFromAccessToken(accessToken).equals(memberId) && isActiveToken(accessToken, accessTokenJwtSecret);
  }

  @Override
  public String generateRefreshToken(String memberId, String email, String username) {
    log.info("(generateRefreshToken)memberId: {}, email: {}, username: {}", memberId, email, username);
    var claims = new HashMap<String, Object>();
    claims.put("email", email);
    claims.put("username", username);
    return generateToken(memberId, claims, refreshTokenLifeTime, refreshTokenJwtSecret);
  }

  @Override
  public String getSubjectFromRefreshToken(String refreshToken) {
    log.debug("(getSubjectFromRefreshToken)refreshToken: {}", refreshToken);
    return getClaim(refreshToken, Claims::getSubject, refreshTokenJwtSecret);
  }

  @Override
  public boolean validateRefreshToken(String refreshToken, String memberId) {
    log.info("(validateRefreshToken)refreshToken: {}, memberId: {}", refreshToken, memberId);
    return getSubjectFromRefreshToken(refreshToken).equals(memberId) && isActiveToken(refreshToken, refreshTokenJwtSecret);
  }

  @Override
  public long getAccessTokenLifeTime() {
    return this.accessTokenLifeTime;
  }

  @Override
  public long getRefreshTokenLifeTime() {
    return this.refreshTokenLifeTime;
  }
}
