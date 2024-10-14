package vn.edu.ptit.supermarket.model.response;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse {

  private String orderId;
  private String paymentMethod;
  private String paymentStatus;
  private String paymentDate;
  private String orderStatus;
  private int totalNumberProducts;
  private BigDecimal totalPriceReduced;
  private float totalVoucherReduced;
  private BigDecimal transportFee;
  private BigDecimal totalAmount;
  private List<ProductInOrderResponse> productInOrderResponses;
  private VoucherInOrderResponse voucherInOrderResponse;
  private ReceiverResponse receiverInOrderResponse;
  private CashierInOrderResponse cashierInOrderResponse;
  private CustomerInOrderResponse customerInOrderResponse;
}
