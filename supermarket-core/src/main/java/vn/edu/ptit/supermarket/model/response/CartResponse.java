package vn.edu.ptit.supermarket.model.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {

  private int totalNumberProducts;
  private List<ProductInCartResponse> productInCartResponses;
}