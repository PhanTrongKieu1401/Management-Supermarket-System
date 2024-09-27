package vn.edu.ptit.supermarket.core_authentication.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import vn.edu.ptit.supermarket.core_authentication.constant.Gender;
import vn.edu.ptit.supermarket.core_authentication.constant.Role;

@Constraint(validatedBy = ValidateRole.RoleValidation.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
public @interface ValidateRole {

  String message() default "Invalid role!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  @Slf4j
  class RoleValidation implements ConstraintValidator<ValidateRole, String> {

    @Override
    public boolean isValid(String role, ConstraintValidatorContext context) {
      log.info("(isValid) is called with role {}", role);
      if (role == null) {
        return true;
      }
      return Arrays.stream(Role.values())
          .anyMatch(roleValue -> roleValue.name().equalsIgnoreCase(role));
    }
  }
}
