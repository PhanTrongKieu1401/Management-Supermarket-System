package vn.edu.ptit.supermarket.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderWSResponse {

  private boolean status;
  private String orderId;
}
