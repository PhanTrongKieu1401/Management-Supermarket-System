package vn.edu.ptit.supermarket.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vn.edu.ptit.supermarket.validation.ValidateOrderStatus;
import vn.edu.ptit.supermarket.validation.ValidatePaymentMethod;
import vn.edu.ptit.supermarket.validation.ValidatePaymentStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequest {

  @NotBlank(message = "Payment method is required")
  @Schema(description = "Phương thức thanh toán", example = "COD")
  @ValidatePaymentMethod
  private String paymentMethod;

  @NotBlank(message = "Payment status is required")
  @Schema(description = "Trạng thái thanh toán", example = "Đã thanh toán")
  @ValidatePaymentStatus
  private String paymentStatus;

  @NotBlank(message = "Order status is required")
  @Schema(description = "Trạng thái đơn hàng", example = "Chờ duyệt")
  @ValidateOrderStatus
  private String orderStatus;

  @NotNull(message = "Total number products is required")
  @Schema(description = "Số lượng mặt hàng", example = "100000")
  private int totalNumberProducts;

  @NotNull(message = "Amount before reduced is required")
  @Schema(description = "Tiền trước giảm", example = "100000")
  private BigDecimal amountBeforeReduced;

  @NotNull(message = "Total price reduced is required")
  @Schema(description = "Tổng tiền giảm trực tiếp", example = "100000")
  private BigDecimal totalPriceReduced;

  @NotNull(message = "Total voucher reduced is required")
  @Schema(description = "Tiền giảm phiếu giảm giá", example = "100000")
  private float totalVoucherReduced;

  @NotNull(message = "Transport fee is required")
  @Schema(description = "Chi phí vận chuyển", example = "100000")
  private BigDecimal transportFee;

  @NotNull(message = "Total amount payable is required")
  @Schema(description = "Tổng tiền thanh toán", example = "100000")
  private BigDecimal totalAmountPayable;

  @Valid
  private List<ProductOrderRequest> products;

  @Valid
  private VoucherOrderRequest voucher;

  @Valid
  private ReceiverOrderRequest receiver;
}
