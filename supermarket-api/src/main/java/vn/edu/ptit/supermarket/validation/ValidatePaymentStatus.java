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
import vn.edu.ptit.supermarket.constant.PaymentStatus;

@Documented
@Constraint(validatedBy = ValidatePaymentStatus.PaymentStatusValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
public @interface ValidatePaymentStatus {

  String message() default "Trạng thái thanh toán không hợp lệ!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  @Slf4j
  class PaymentStatusValidation implements ConstraintValidator<ValidatePaymentStatus, String> {

    @Override
    public boolean isValid(String paymentStatus, ConstraintValidatorContext context) {
      log.info("(isValid) is called with payment status {}", paymentStatus);
      if (paymentStatus == null) {
        return true;
      }
      return Arrays.stream(PaymentStatus.values())
          .anyMatch(paymentStatusValue -> paymentStatusValue.name().equalsIgnoreCase(paymentStatus));
    }
  }
}
