package vn.edu.ptit.supermarket.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CashierInOrderResponse {

  private String id;
  private String firstName;
  private String middleName;
  private String lastName;
}
