package vn.edu.ptit.supermarket.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReceiverResponse {

  private String fullName;
  private String phone;
  private String addressDetail;
  private String ward;
  private String district;
  private String province;
}
