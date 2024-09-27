package vn.edu.ptit.supermarket.core_authentication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "address")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Address extends BaseEntity {

  private String addressDetail;
  private String ward;
  private String district;
  private String province;
}
