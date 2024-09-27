package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.ptit.supermarket.core_authentication.entity.BaseEntity;

@Data
@Entity
@Table(name = "supplier")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Supplier extends BaseEntity {

  private String name;
  private String phone;
  private String email;
  private String addressDetail;
  private String ward;
  private String district;
  private String province;
  private boolean isActivated = true;
}
