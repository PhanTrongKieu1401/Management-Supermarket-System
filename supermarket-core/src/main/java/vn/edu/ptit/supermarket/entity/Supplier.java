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
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "supplier")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Supplier {

  @Id
  private String id;

  private String name;

  @Column(length = 10, nullable = false)
  private String phone;
  private String email;
  private String addressDetail;
  private String ward;
  private String district;
  private String province;
  private boolean isActivated = true;

  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdAt;

  @Column(nullable = false)
  @LastModifiedDate
  private LocalDateTime lastUpdatedAt;


  public Supplier(String name, String phone, String email, String addressDetail,
      String ward, String district, String province) {
    this.id = "S" + System.currentTimeMillis();
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.addressDetail = addressDetail;
    this.ward = ward;
    this.district = district;
    this.province = province;
  }
}
