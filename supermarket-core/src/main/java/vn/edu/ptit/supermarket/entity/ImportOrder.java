package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.ptit.supermarket.constant.PaymentStatus;
import vn.edu.ptit.supermarket.core_authentication.entity.BaseEntity;

@Data
@Entity
@Table(name = "import_order")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ImportOrder extends BaseEntity {

  private LocalDateTime importDate;
  private Enum<PaymentStatus> paymentStatus;
  private String supplierId;
  private String managerId;
}
