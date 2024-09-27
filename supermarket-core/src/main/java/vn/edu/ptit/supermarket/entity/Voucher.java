package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.ptit.supermarket.core_authentication.entity.BaseEntity;

@Data
@Entity
@Table(name = "voucher")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Voucher extends BaseEntity {

  private String voucherCode;
  private float value;
  private float conditionsApply;
  private Date releaseDate;
  private Date expireDate;
  private boolean isActivated = true;
}
