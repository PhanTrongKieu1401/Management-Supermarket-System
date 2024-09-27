package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;


@Data
@Entity
@Table(name = "product_in_import_order")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ProductInImportOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private int quantity;
  private BigDecimal priceImport;
  private String importOrderId;
  private String productId;

  @CreatedDate
  private LocalDateTime createdAt;
}
