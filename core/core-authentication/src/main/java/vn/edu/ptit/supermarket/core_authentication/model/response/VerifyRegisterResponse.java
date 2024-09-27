package vn.edu.ptit.supermarket.core_authentication.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerifyRegisterResponse {

  private String registerKey;
}
