package vn.edu.ptit.supermarket.model.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPageResponse {

  private List<ProductResponse> productResponses;
  private int totalPages;
}
