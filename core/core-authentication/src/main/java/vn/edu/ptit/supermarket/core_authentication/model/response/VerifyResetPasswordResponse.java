package vn.edu.ptit.supermarket.core_authentication.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerifyResetPasswordResponse {

  private String resetPasswordKey;
}