package vn.edu.ptit.supermarket.core_exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

  private String code;
  private Object message;

  public static MessageResponse of(String code, Object message) {
    MessageResponse response = new MessageResponse();
    response.setCode(code);
    response.setMessage(message);
    return response;
  }
}
