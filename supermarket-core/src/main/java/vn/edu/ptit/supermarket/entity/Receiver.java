package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "receiver")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Receiver{

  @Id
  private String id;

  private String fullName;

  @Column(length = 10)
  private String phone;

  private String addressDetail;
  private String ward;
  private String district;
  private String province;
  private String orderId;

  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdAt;

  public Receiver(String fullName, String phone, String addressDetail, String ward, String district, String province, String orderId) {
    this.id = "R" + System.currentTimeMillis() + phone.substring((phone.length() - 3));
    this.fullName = fullName;
    this.phone = phone;
    this.addressDetail = addressDetail;
    this.ward = ward;
    this.district = district;
    this.province = province;
    this.orderId = orderId;
  }
}
