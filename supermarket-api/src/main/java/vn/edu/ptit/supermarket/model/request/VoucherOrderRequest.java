package vn.edu.ptit.supermarket.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

  @NotBlank(message = "Voucher id is required")
  @Schema(description = "Id voucher", example = "100000")
  private String voucherId;

  @NotBlank(message = "Voucher code is required")
  @Schema(description = "Mã voucher", example = "100000")
  private String voucherCode;

  @NotNull(message = "Voucher value is required")
  @Schema(description = "Giá trị voucher", example = "100000")
  private float value;

  @NotNull(message = "Conditions apply is required")
  @Schema(description = "Điều kiện áp dụng", example = "100000")
  private float conditionsApply;

  @NotNull(message = "Expire date is required")
  @Schema(description = "Ngày hết hạn", example = "100000")
  private Date expireDate;
}
