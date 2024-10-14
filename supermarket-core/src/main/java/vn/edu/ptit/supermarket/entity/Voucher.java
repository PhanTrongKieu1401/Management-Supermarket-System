package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "voucher")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Voucher{

  @Id
  private String id;

  private String voucherCode;
  private float value;
  private float conditionsApply;
  private Date releaseDate;
  private Date expireDate;
  private boolean isActivated = true;

  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdAt;

  @Column(nullable = false)
  @LastModifiedDate
  private LocalDateTime lastUpdatedAt;


  public Voucher(float value, float conditionsApply, Date releaseDate, Date expireDate) {
    this.voucherCode = "V" + System.currentTimeMillis() + value/1000 + "K";
    this.value = value;
    this.conditionsApply = conditionsApply;
    this.releaseDate = releaseDate;
    this.expireDate = expireDate;
  }
}
