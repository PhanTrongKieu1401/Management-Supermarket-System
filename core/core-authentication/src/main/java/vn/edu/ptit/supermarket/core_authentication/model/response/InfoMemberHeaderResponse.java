package vn.edu.ptit.supermarket.core_authentication.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InfoMemberHeaderResponse {

  private String firstName;
  private String middleName;
  private String lastName;
  private String role;
}
