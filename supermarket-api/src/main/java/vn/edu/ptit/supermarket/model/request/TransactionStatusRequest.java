package vn.edu.ptit.supermarket.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionStatusRequest {

  private String partnerCode;
  private String orderId;
  private int resultCode;
}
