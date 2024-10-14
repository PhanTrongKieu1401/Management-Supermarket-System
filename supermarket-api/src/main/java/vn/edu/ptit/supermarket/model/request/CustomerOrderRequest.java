package vn.edu.ptit.supermarket.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderRequest {

  private String authorId;
  private String customerId;
  private OrderRequest orderRequest;
}
