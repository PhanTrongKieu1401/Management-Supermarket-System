package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import vn.edu.ptit.supermarket.core_authentication.entity.BaseEntity;

@Data
@Entity
@Table(name = "receiver")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Receiver{

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String fullName;
  private String phone;
  private String addressDetail;
  private String ward;
  private String district;
  private String province;
  private String orderId;
  @CreatedDate
  private LocalDateTime createdAt;
}
