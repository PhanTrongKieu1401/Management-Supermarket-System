package vn.edu.ptit.supermarket.validation;

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
import vn.edu.ptit.supermarket.constant.PaymentMethod;

@Documented
@Constraint(validatedBy = ValidatePaymentMethod.PaymentMethodValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
public @interface ValidatePaymentMethod {

  String message() default "Phương thức thanh toán không hợp lệ!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  @Slf4j
  class PaymentMethodValidation implements ConstraintValidator<ValidatePaymentMethod, String> {

    @Override
    public boolean isValid(String paymentMethod, ConstraintValidatorContext context) {
      log.info("(isValid) is called with payment method {}", paymentMethod);
      if (paymentMethod == null) {
        return true;
      }
      return Arrays.stream(PaymentMethod.values())
          .anyMatch(paymentMethodValue -> paymentMethodValue.name().equalsIgnoreCase(paymentMethod));
    }
  }
}
