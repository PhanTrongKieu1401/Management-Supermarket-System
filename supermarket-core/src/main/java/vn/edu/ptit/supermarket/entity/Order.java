package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.ptit.supermarket.constant.OrderStatus;
import vn.edu.ptit.supermarket.constant.PaymentStatus;
import vn.edu.ptit.supermarket.core_authentication.entity.BaseEntity;

@Data
@Entity
@Table(name = "order")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Order extends BaseEntity {

  private String paymentMethod;
  private LocalDateTime paymentDate;
  private Enum<PaymentStatus> paymentStatus;
  private Enum<OrderStatus> orderStatus;
  private BigDecimal transportFee;
  private String customerId;
  private String cashierId;
  private String voucherId;
}
