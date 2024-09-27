package vn.edu.ptit.supermarket.core_authentication.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import vn.edu.ptit.supermarket.core_authentication.validation.ValidateGender;
import vn.edu.ptit.supermarket.core_authentication.validation.ValidateLocalDate;
import vn.edu.ptit.supermarket.core_authentication.validation.ValidatePhone;
import vn.edu.ptit.supermarket.core_authentication.validation.ValidateRole;

@Data
public class UpdateMemberRequest {

  @NotBlank(message = "First name is required")
  @Schema(description = "First name", example = "Nguyen")
  private String firstName;

  @NotBlank(message = "Middle name is required")
  @Schema(description = "Middle name", example = "Tran")
  private String middleName;

  @NotBlank(message = "Last name is required")
  @Schema(description = "Last name", example = "Nguyen")
  private String lastName;

  @NotBlank(message = "Phone is required")
  @Schema(description = "Phone", example = "0123456789")
  @ValidatePhone
  private String phone;

  @NotBlank(message = "Date of birth is required")
  @Schema(description = "Date of birth", example = "2000-01-01")
  @ValidateLocalDate
  private String dateOfBirth;

  @NotBlank(message = "Gender is required")
  @Schema(description = "Gender", example = "Male")
  @ValidateGender
  private String gender;

  @NotBlank(message = "Role is required")
  @ValidateRole
  @Schema(description = "Role", example = "CUSTOMER")
  private String role;
}
