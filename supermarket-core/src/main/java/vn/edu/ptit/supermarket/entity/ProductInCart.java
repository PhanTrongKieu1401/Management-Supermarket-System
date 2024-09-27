package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import vn.edu.ptit.supermarket.core_authentication.entity.BaseEntity;

@Data
@Entity
@Table(name = "product_in_cart")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ProductInCart extends BaseEntity {

  private int quantity;
  private String productId;
  private String cartId;
}
