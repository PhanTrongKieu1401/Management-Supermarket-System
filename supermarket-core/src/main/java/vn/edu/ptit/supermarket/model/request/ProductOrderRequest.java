package vn.edu.ptit.supermarket.model.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderRequest {

  private String productId;
  private String name;
  private String image;
  private String size;
  private BigDecimal priceSell;
  private BigDecimal discountSell;
  private int quantityInStock;
  private int quantity;
  private boolean selected;
}
