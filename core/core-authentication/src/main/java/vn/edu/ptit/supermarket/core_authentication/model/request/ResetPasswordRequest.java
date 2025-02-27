package vn.edu.ptit.supermarket.core_authentication.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vn.edu.ptit.supermarket.core_authentication.validation.ValidateEmail;
import vn.edu.ptit.supermarket.core_authentication.validation.ValidatePassword;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordRequest {

  @NotBlank(message = "email is required")
  @ValidateEmail
  @Schema(description = "Email", example = "QpCqA@gmail.com")
  private String email;
  @NotBlank(message = "resetPasswordKey is required")
  @Schema(description = "Reset password key", example = "dafagagagasgas==")
  private String resetPasswordKey;
  @NotBlank(message = "password is required")
  @ValidatePassword
  @Schema(description = "Password", example = "Password2024@")
  private String password;
  @NotBlank(message = "confirmPassword is required")
  @ValidatePassword
  @Schema(description = "Confirm Password", example = "Password2024@")
  private String confirmPassword;
}
