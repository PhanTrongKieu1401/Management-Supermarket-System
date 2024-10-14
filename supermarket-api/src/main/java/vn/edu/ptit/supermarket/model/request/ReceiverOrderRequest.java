package vn.edu.ptit.supermarket.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReceiverOrderRequest {

  @NotBlank(message = "Full name is required")
  @Schema(description = "Tên người nhận", example = "Nguyễn Thế Hùng")
  private String fullName;

  @NotBlank(message = "Phone is required")
  @Schema(description = "SDT người nhận", example = "0123456789")
  private String phone;

  @NotBlank(message = "Address detail is required")
  @Schema(description = "Địa chi chủ", example = "Địa chỉ 1")
  private String addressDetail;

  @NotBlank(message = "Ward is required")
  @Schema(description = "Phường/xã", example = "Mộ Lao")
  private String ward;

  @NotBlank(message = "District is required")
  @Schema(description = "Quận/huyện", example = "Hà Đông")
  private String district;

  @NotBlank(message = "Province is required")
  @Schema(description = "Tỉnh/thành phố", example = "Hà Nội")
  private String province;
}
