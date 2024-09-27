package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.ptit.supermarket.core_authentication.entity.Member;

@Data
@Entity
@Table(name = "customer")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Customer extends Member {

  private String memberCode;
  private int point;
}
