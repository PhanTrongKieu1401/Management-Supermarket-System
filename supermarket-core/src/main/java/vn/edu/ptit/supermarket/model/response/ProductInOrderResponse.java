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
public class ProductInOrderResponse {

  private String productId;
  private String name;
  private String image;
  private BigDecimal priceSell;
  private BigDecimal discountSell;
  private BigDecimal priceImport;
  private int quantity;
  private BigDecimal amount;

  public ProductInOrderResponse(String productId, String name, String image, BigDecimal priceSell, BigDecimal discountSell, BigDecimal priceImport, int quantity) {
    this.productId = productId;
    this.name = name;
    this.image = image;
    this.priceSell = priceSell;
    this.discountSell = discountSell;
    this.priceImport = priceImport;
    this.quantity = quantity;
    if(discountSell.compareTo(BigDecimal.ZERO) > 0) {
      this.amount = discountSell.multiply(BigDecimal.valueOf(quantity));
    } else {
      this.amount = priceSell.multiply(BigDecimal.valueOf(quantity));
    }
  }
}
