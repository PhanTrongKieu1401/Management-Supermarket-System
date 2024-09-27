package vn.edu.ptit.supermarket.core_authentication.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.supermarket.core_authentication.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

  Optional<Member> findByAccountId(String accountId);

  boolean existsByEmail(String email);

  boolean existsByAccountId(String accountId);
}
