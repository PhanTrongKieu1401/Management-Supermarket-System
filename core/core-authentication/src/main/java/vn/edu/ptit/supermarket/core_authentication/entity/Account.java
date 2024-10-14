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
@Table(name = "account")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Account {

  @Id
  @Column(unique = true)
  private String id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private Boolean isActivated = false;

  @Column(nullable = false)
  private Boolean isLockedPermanent= false;

  @Column(nullable = false)
  private Boolean isFistLogin = true;

  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdAt;

  @Column(nullable = false)
  @LastModifiedDate
  private LocalDateTime lastUpdatedAt;

  public static Account of(String username, String password) {
    return Account.of(username, password, true, false, true);
  }

  public static Account of(String username, String password, boolean isActivated,
      boolean isLockedPermanent, boolean isFistLogin) {
    Account account = new Account();
    account.id = "AC" + System.currentTimeMillis();
    account.username = username;
    account.password = password;
    account.isActivated = isActivated;
    account.isLockedPermanent = isLockedPermanent;
    account.isFistLogin = isFistLogin;
    return account;
  }
}
