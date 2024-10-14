package vn.edu.ptit.supermarket.core_authentication.entity;

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
@Table(name = "address")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Address {

  @Id
  @Column(unique = true)
  private String id;

  @Column(nullable = false)
  private String addressDetail;

  @Column(nullable = false)
  private String ward;

  @Column(nullable = false)
  private String district;

  @Column(nullable = false)
  private String province;

  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdAt;

  @Column(nullable = false)
  @LastModifiedDate
  private LocalDateTime lastUpdatedAt;

  public static Address of(String addressDetail, String ward, String district, String province) {
    Address address = new Address();
    address.id = "ADR" + System.currentTimeMillis();
    address.addressDetail = addressDetail;
    address.ward = ward;
    address.district = district;
    address.province = province;
    return address;
  }
}
