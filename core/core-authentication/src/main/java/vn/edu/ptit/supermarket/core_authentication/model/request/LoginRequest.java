package vn.edu.ptit.supermarket.core_authentication.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import vn.edu.ptit.supermarket.core_authentication.validation.ValidatePassword;

@Data
public class LoginRequest {

  @NotBlank(message = "Username is required")
  @Schema(description = "Username", example = "admin")
  @Size(min = 8, max = 20, message = "Username must be between 8 and 20 characters")
  private String username;

  @NotBlank(message = "Password is required")
  @Schema(description = "Password", example = "admin123")
  @ValidatePassword
  private String password;
}
