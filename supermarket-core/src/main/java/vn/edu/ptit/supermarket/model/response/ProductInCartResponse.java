package vn.edu.ptit.supermarket.model.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductInCartResponse {

  private String productId;
  private String name;
  private String image;
  private String size;
  private BigDecimal priceSell;
  private BigDecimal discountSell;
  private int quantityInStock;
  private int quantity;
  private boolean selected = false;

  public ProductInCartResponse(String productId, String name, String image, String size,
      BigDecimal priceSell, BigDecimal discountSell, int quantityInStock, int quantity) {
    this.productId = productId;
    this.name = name;
    this.image = image;
    this.size = size;
    this.priceSell = priceSell;
    this.discountSell = (discountSell != null) ? discountSell : BigDecimal.ZERO;
    this.quantityInStock = quantityInStock;
    this.quantity = quantity;
  }
}
