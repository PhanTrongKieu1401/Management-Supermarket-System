package vn.edu.ptit.supermarket.core_authentication.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.supermarket.core_authentication.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

  @Query("""
      SELECT a FROM Account a where a.isActivated = true""")
  Account getAccountActivated();

  @Query("""
      SELECT a FROM Account a
      JOIN Member m ON  a.id = m.accountId AND m.id = :memberId
      """)
  Account findByMemberId(String memberId);

  Account findByUsername(String username);

  @Query("""
      SELECT a FROM Account a
      JOIN Member m ON  a.id = m.accountId AND m.email = :email
      """)
  Account findByEmail(String email);

  @Query("""
      SELECT a.username FROM Account a
      JOIN Member m ON  a.id = m.accountId AND m.id = :memberId
      """)
  String findUsernameByMemberId(String memberId);

  @Query("""
      UPDATE Account a
      SET a.isLockedPermanent = :isLocked
      WHERE a.id = :accountId
      """)
  void updateLockAccount(String accountId, boolean isLocked);

  boolean existsByUsername(String username);
}
