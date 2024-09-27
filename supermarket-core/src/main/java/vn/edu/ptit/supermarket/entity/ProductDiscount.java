package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.ptit.supermarket.core_authentication.entity.BaseEntity;

@Data
@Entity
@Table(name = "product_discount")
@NoArgsConstructor
@AllArgsConstructor
public class ProductDiscount extends BaseEntity {

  private BigDecimal discountSell;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private String productId;
}
