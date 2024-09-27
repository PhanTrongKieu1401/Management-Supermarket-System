package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.ptit.supermarket.core_authentication.entity.BaseEntity;

@Data
@Entity
@Table(name = "evaluation")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Evaluation extends BaseEntity {

  private int point;
  private String customerId;
  private String productId;
}
