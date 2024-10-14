package vn.edu.ptit.supermarket.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReceiverOrderRequest {

  private String fullName;
  private String phone;
  private String addressDetail;
  private String ward;
  private String district;
  private String province;
}
