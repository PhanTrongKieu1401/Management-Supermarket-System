package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "product_in_order")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ProductInOrder {

  @Id
  private String id;

  private int quantity;

  @Column(precision = 10, scale = 1, nullable = false)
  private BigDecimal priceSell;

  @Column(precision = 10, scale = 1, nullable = false)
  private BigDecimal discountSell;

  @Column(precision = 10, scale = 1, nullable = false)
  private BigDecimal priceImport;

  private String orderId;
  private String productId;
}
