package vn.edu.ptit.supermarket.core_authentication.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.ptit.supermarket.core_authentication.model.request.RegisterRequest;
import vn.edu.ptit.supermarket.core_authentication.model.request.UpdateMemberRequest;

@Data
@Entity
@Table(name = "member")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Member extends BaseEntity{

  private String firstName;
  private String middleName;
  private String lastName;
  private String email;
  private String phone;
  private Date dateOfBirth;
  private String gender;
  private String role;
  private String accountId;
  private String addressId;

  public static Member from(String email, String accountId) {
    var member = new Member();
    member.setEmail(email);
    member.setAccountId(accountId);
    return member;
  }

  public static Member from(RegisterRequest registerRequest, String accountId, String addressId) {
    var member = new Member();
    member.setEmail(registerRequest.getEmail());
    member.setFirstName(registerRequest.getFirstName());
    member.setMiddleName(registerRequest.getMiddleName());
    member.setLastName(registerRequest.getLastName());
    member.setPhone(registerRequest.getPhone());
    member.setDateOfBirth(Date.valueOf(registerRequest.getDateOfBirth()));
    member.setGender(registerRequest.getGender());
    member.setRole(registerRequest.getRole());
    member.setAccountId(accountId);
    member.setAddressId(addressId);
    return member;
  }

  public static Member from(String memberId, UpdateMemberRequest updateMemberRequest) {
    var member = new Member();
    member.setId(memberId);
    member.setFirstName(updateMemberRequest.getFirstName());
    member.setMiddleName(updateMemberRequest.getMiddleName());
    member.setLastName(updateMemberRequest.getLastName());
    member.setPhone(updateMemberRequest.getPhone());
    member.setDateOfBirth(Date.valueOf(updateMemberRequest.getDateOfBirth()));
    member.setGender(updateMemberRequest.getGender());
    member.setRole(updateMemberRequest.getRole());
    return member;
  }
}
