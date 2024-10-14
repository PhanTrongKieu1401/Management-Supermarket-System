package vn.edu.ptit.supermarket.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

  @NotBlank(message = "Product id is required")
  @Schema(description = "Mã sản phẩm", example = "1")
  private String productId;

  @NotBlank(message = "Name is required")
  @Schema(description = "Tên sản phẩm", example = "Iphone 13")
  private String name;

  @NotBlank(message = "Image is required")
  @Schema(description = "Hình ảnh", example = "Base64")
  private String image;

  @NotBlank(message = "Size is required")
  @Schema(description = "Kích thước", example = "Iphone 13")
  private String size;

  @NotNull(message = "Price sell is required")
  @Schema(description = "Gia bán", example = "100000")
  private BigDecimal priceSell;

  @NotNull(message = "Discount sell is required")
  @Schema(description = "Gia bán khác", example = "100000")
  private BigDecimal discountSell;

  @NotNull(message = "Quantity in stock is required")
  @Schema(description = "Số luồng trong kho", example = "10")
  private int quantityInStock;

  @NotNull(message = "Quantity is required")
  @Schema(description = "Số lượng", example = "10")
  private int quantity;

  @Schema(description = "Chọn", example = "true")
  private boolean selected;
}
