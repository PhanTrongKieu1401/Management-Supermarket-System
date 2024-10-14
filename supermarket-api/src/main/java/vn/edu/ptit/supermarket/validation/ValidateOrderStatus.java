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
import vn.edu.ptit.supermarket.constant.OrderStatus;

@Documented
@Constraint(validatedBy = ValidateOrderStatus.OrderStatusValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
public @interface ValidateOrderStatus {

  String message() default "Trạng thái đơn hàng không hợp lệ!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  @Slf4j
  class OrderStatusValidation implements ConstraintValidator<ValidateOrderStatus, String> {

    @Override
    public boolean isValid(String orderStatus, ConstraintValidatorContext context) {
      log.info("(isValid) is called with order status {}", orderStatus);
      if (orderStatus == null) {
        return true;
      }
      return Arrays.stream(OrderStatus.values())
          .anyMatch(orderStatusValue -> orderStatusValue.name().equalsIgnoreCase(orderStatus));
    }
  }
}
