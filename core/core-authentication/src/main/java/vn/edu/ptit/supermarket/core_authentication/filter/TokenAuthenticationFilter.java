package vn.edu.ptit.supermarket.core_authentication.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
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
    try {
      memberId = authTokenService.getSubjectFromAccessToken(jwtToken);
    } catch (Exception ex) {
      log.error("(doFilterInternal)request: {}, response: {}, filterChain: {}", request, response, filterChain);
      filterChain.doFilter(request, response);
      return;
    }

    if(Objects.nonNull(memberId) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
      var user = memberService.findById(memberId);
      var account = accountService.findByMemberId(memberId);
      if(authTokenService.validateAccessToken(jwtToken, memberId)) {
        var usernamePasswordAuthToken =
            new UsernamePasswordAuthenticationToken(
                account.getUsername(), user.getId(), new ArrayList<>());
        usernamePasswordAuthToken.setDetails(user);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}
