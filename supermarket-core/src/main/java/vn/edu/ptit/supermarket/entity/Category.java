package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.ptit.supermarket.core_authentication.entity.BaseEntity;

@Data
@Entity
@Table(name = "category")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Category extends BaseEntity {

  private String name;
  private boolean isActivated = true;
}
