package vn.edu.ptit.supermarket.model.request;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoucherOrderRequest {

  private String voucherId;
  private String voucherCode;
  private float value;
  private float conditionsApply;
  private Date expireDate;
}
