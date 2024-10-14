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

  @Value("${application.authentication.access_token.jwt_secret}")
  private String accessTokenJwtSecret;

  @Value("${application.authentication.access_token.life_time}")
  private Long accessTokenLifeTime;

  @Value("${application.authentication.refresh_token.jwt_secret}")
  private String refreshTokenJwtSecret;

  @Value("${application.authentication.refresh_token.life_time}")
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

  public <T> T getClaim(String token, Function<Claims, T> claimsResolve, String secretKey) {
    return claimsResolve.apply(getClaims(token, secretKey));
  }

  private Claims getClaims(String token, String secretKey) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
  }

  @Override
  public Claims getClaimsFromAccessToken(String accessToken) {
    return Jwts.parser().setSigningKey(accessTokenJwtSecret).parseClaimsJws(accessToken).getBody();
  }

  @Override
  public String generateAccessToken(String memberId, String email, String role, String username) {
    log.info("(generateAccessToken)memberId: {}, email: {}, role: {}, username: {}", memberId, email, role, username);
    var claims = new HashMap<String, Object>();
    claims.put("role", role);
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
    try {
      String subject = getSubjectFromAccessToken(accessToken);
      boolean isTokenActive = isActiveToken(accessToken, accessTokenJwtSecret);
      return subject.trim().equals(memberId.trim()) && isTokenActive;
    } catch (Exception e) {
      log.error("Error validating access token: {}", e.getMessage());
      return false;
    }
  }

  @Override
  public String generateRefreshToken(String memberId, String email, String role, String username) {
    log.info("(generateRefreshToken)memberId: {}, email: {}, role: {}, username: {}", memberId, email, role, username);
    var claims = new HashMap<String, Object>();
    claims.put("role", role);
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
