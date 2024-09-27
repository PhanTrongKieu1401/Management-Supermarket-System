package vn.edu.ptit.supermarket.core_authentication.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityUtil {

  public static String getMemberId() {
    log.info("(getMemberId)");
    if (SecurityContextHolder.getContext().getAuthentication() == null) {
      return "SYSTEM_ID";
    }
    log.info("(getMemberId)id: {}", SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
    return SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
  }
}
