package vn.edu.ptit.supermarket.core_authentication.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vn.edu.ptit.supermarket.core_authentication.validation.ValidateEmail;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResendOtpRequest {

  @NotBlank(message = "Email is required")
  @ValidateEmail
  @Schema(description = "Email", example = "QpCqA@gmail.com")
  private String email;

  @NotBlank(message = "type is required")
  @Schema(description = "Type", example = "FORGOT")
  private String type;
}
