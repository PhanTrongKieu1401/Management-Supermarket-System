package vn.edu.ptit.supermarket.model.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductResponse {
  private String id;
  private String name;
  private String image;
  private String size;
  private BigDecimal priceSell;
  private BigDecimal discountSell;
  private int quantityInStock;
  private String description;
  private double rate;
  private String category;

  public ProductResponse(String id, String name, String image, String size, BigDecimal priceSell, BigDecimal discountSell,
      int quantityInStock, String description, double rate, String category) {
    this.id = id;
    this.name = name;
    this.image = image;
    this.size = size;
    this.priceSell = priceSell;
    this.discountSell = discountSell;
    this.quantityInStock = quantityInStock;
    this.description = description;
    this.rate = rate;
    this.category = category;
  }
}
