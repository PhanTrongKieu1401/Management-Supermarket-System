package vn.edu.ptit.supermarket.core_authentication.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.supermarket.core_authentication.entity.Member;
import vn.edu.ptit.supermarket.core_authentication.model.response.InfoMemberHeaderResponse;
import vn.edu.ptit.supermarket.core_authentication.model.response.InfoMemberResponse;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

  Optional<Member> findByAccountId(String accountId);

  boolean existsByEmail(String email);

  boolean existsByAccountId(String accountId);

  @Query("""
      select new vn.edu.ptit.supermarket.core_authentication.model.response.InfoMemberHeaderResponse(
        m.firstName, m.middleName, m.lastName, m.role
      ) from Member m where m.id = :memberId
      """)
  InfoMemberHeaderResponse getFullName(String memberId);

  @Query("""
  select new vn.edu.ptit.supermarket.core_authentication.model.response.InfoMemberResponse(
    m.firstName, m.middleName, m.lastName, m.email, m.phone, m.dateOfBirth, m.gender, m.role
  ) from Member m
  where m.id = :memberId
  """)
  InfoMemberResponse getInfo(String memberId);
}
