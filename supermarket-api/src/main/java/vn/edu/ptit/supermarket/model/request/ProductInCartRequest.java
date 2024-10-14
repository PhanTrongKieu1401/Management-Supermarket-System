package vn.edu.ptit.supermarket.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductInCartRequest {

  @NotBlank(message = "ProductId is required")
  @Schema(description = "ProductId", example = "Product1")
  private String productId;

  @NotNull(message = "Quantity is required")
  @Schema(description = "Quantity", example = "1")
  private int quantity;
}
