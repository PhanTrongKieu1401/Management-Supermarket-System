package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "`order`")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Order {

  @Id
  private String id;

  private String paymentMethod;
  private LocalDateTime paymentDate;
  private String paymentStatus;
  private String orderStatus;

  @Column(name = "transport_fee", precision = 10, scale = 1, nullable = false)
  private BigDecimal transportFee;
  private String customerId;
  private String staffId;
  private String voucherId;

  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdAt;

  @Column(nullable = false)
  @LastModifiedDate
  private LocalDateTime lastUpdatedAt;

  public Order(String id, String paymentMethod, LocalDateTime paymentDate, String paymentStatus,
      String orderStatus, BigDecimal transportFee, String customerId, String staffId,
      String voucherId) {
    this.id = id;
    this.paymentMethod = paymentMethod;
    this.paymentDate = paymentDate;
    this.paymentStatus = paymentStatus;
    this.orderStatus = orderStatus;
    this.transportFee = transportFee;
    this.customerId = customerId;
    this.staffId = staffId;
    this.voucherId = voucherId;
  }
}
