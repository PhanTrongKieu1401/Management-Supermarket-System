package vn.edu.ptit.supermarket.core_authentication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "account")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Account extends BaseEntity{

  private String username;
  private String password;
  private Boolean isActivated = false;
  private Boolean isLockedPermanent= false;
  private Boolean isFistLogin = true;

  public static Account of(String username, String password) {
    return Account.of(username, password, true, false, true);
  }
}
