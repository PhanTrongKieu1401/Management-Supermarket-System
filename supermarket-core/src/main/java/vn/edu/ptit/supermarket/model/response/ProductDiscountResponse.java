package vn.edu.ptit.supermarket.model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ProductDiscountResponse {

  private String productDiscountId;
  private BigDecimal discountSell;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
}
