package vn.edu.ptit.supermarket.model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

  private String orderId;
  private String paymentMethod;
  private String paymentStatus;
  private String paymentDate;
  private String orderStatus;
  private BigDecimal totalAmount;

  public OrderResponse(String orderId, String paymentMethod, String paymentStatus,
      LocalDateTime paymentDate, String orderStatus,BigDecimal transportFee, long totalAmount) {
    this.orderId = orderId;
    this.paymentMethod = paymentMethod;
    this.paymentStatus = paymentStatus;
    this.paymentDate = paymentDate.toLocalDate().toString();
    this.orderStatus = orderStatus;
    this.totalAmount = BigDecimal.valueOf(totalAmount).add(transportFee);
  }
}
