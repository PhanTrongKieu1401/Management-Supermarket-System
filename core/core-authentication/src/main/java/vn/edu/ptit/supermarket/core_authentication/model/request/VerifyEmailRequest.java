package vn.edu.ptit.supermarket.core_authentication.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import vn.edu.ptit.supermarket.core_authentication.validation.ValidateEmail;

@Data
public class VerifyEmailRequest {

  @NotBlank(message = "Email is required")
  @ValidateEmail
  @Schema(description = "Email", example = "xg6g6@gmail.com")
  private String email;
}
