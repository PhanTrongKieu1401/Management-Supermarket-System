package vn.edu.ptit.supermarket.core_authentication.model.response;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InfoMemberResponse {

  private String firstName;
  private String middleName;
  private String lastName;
  private String email;
  private String phone;
  private Date dateOfBirth;
  private String gender;
  private String role;
}
