package vn.edu.ptit.supermarket.core_authentication.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import vn.edu.ptit.supermarket.core_authentication.service.AccountService;
import vn.edu.ptit.supermarket.core_authentication.service.AuthTokenService;
import vn.edu.ptit.supermarket.core_authentication.service.MemberService;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

  private final AuthTokenService authTokenService;
  private final MemberService memberService;
  private final AccountService accountService;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {
    log.info("(doFilterInternal)request: {}, response: {}, filterChain: {}", request, response, filterChain);
    final String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (Objects.isNull(accessToken)) {
      filterChain.doFilter(request, response);
      return;
    }

    if (!accessToken.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    var jwtToken = accessToken.substring(7);
    String memberId;
    Claims claims;
    try {
      memberId = authTokenService.getSubjectFromAccessToken(jwtToken);
      claims = authTokenService.getClaimsFromAccessToken(jwtToken);
      System.out.println(claims);
    } catch (Exception ex) {
      log.error("(doFilterInternal)request: {}, response: {}, filterChain: {}", request, response, filterChain);
      filterChain.doFilter(request, response);
      return;
    }

    if(Objects.nonNull(memberId) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
      var user = memberService.findById(memberId);
      var account = accountService.findByMemberId(memberId);

      if(authTokenService.validateAccessToken(jwtToken, memberId)) {
        String role = claims.get("role", String.class);
        String username = claims.get("username", String.class);
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
            .username(username)
            .password("")
            .authorities(Collections.singleton(() -> "ROLE_" + role))
            .build();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            userDetails, memberId, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
//        var usernamePasswordAuthToken =
//            new UsernamePasswordAuthenticationToken(
//                account.getUsername(), user.getId(), new ArrayList<>());
//        usernamePasswordAuthToken.setDetails(user);

//        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthToken);

      }
    }
    filterChain.doFilter(request, response);
  }
}
