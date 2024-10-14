package vn.edu.ptit.supermarket.core_authentication.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import vn.edu.ptit.supermarket.core_authentication.validation.ValidateEmail;
import vn.edu.ptit.supermarket.core_authentication.validation.ValidatePassword;
import vn.edu.ptit.supermarket.core_authentication.validation.ValidatePhone;
import vn.edu.ptit.supermarket.core_authentication.validation.ValidateRole;

@Data
public class RegisterRequest {

  @NotBlank(message = "registerKey is required")
  @Schema(description = "Register key", example = "dafagagagasgas==")
  private String registerKey;

  @NotBlank(message = "email is required")
  @ValidateEmail
  @Schema(description = "Email", example = "QpCqA@gmail.com")
  private String email;

  @NotBlank(message = "username is required")
  @Size(min = 5, max = 20, message = "username must be between 8 and 20 character")
  @Schema(description = "Username", example = "username")
  private String username;

  @NotBlank(message = "password is required")
  @ValidatePassword
  @Schema(description = "Password", example = "Password2024@")
  private String password;

  @NotBlank(message = "confirmPassword is required")
  @ValidatePassword
  @Schema(description = "Confirm Password", example = "Password2024@")
  private String confirmPassword;

  @NotBlank(message = "First name is required")
  @Schema(description = "First name", example = "John")
  private String firstName;

  @Schema(description = "Middle name", example = "Doe")
  private String middleName;

  @NotBlank(message = "Last name is required")
  @Schema(description = "Last name", example = "Doe")
  private String lastName;

  @NotBlank(message = "Phone is required")
  @ValidatePhone
  @Schema(description = "Phone", example = "0123456789")
  private String phone;

  @NotBlank(message = "Role is required")
  @ValidateRole
  @Schema(description = "Role", example = "CUSTOMER")
  private String role;
}
