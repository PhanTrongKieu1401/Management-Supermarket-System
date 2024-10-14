package vn.edu.ptit.supermarket.model.request;

import java.math.BigDecimal;
import java.util.List;
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
public class OrderRequest {

  private String paymentMethod;
  private String paymentStatus;
  private String orderStatus;
  private int totalNumberProducts;
  private BigDecimal amountBeforeReduced;
  private BigDecimal totalPriceReduced;
  private float totalVoucherReduced;
  private BigDecimal transportFee;
  private BigDecimal totalAmountPayable;
  private List<ProductOrderRequest> products;
  private VoucherOrderRequest voucher;
  private ReceiverOrderRequest receiver;
}
