package vn.edu.ptit.supermarket.core_exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ErrorResponse {

  private String code;
  private long timestamp;
  private MessageResponse message;

  public static ErrorResponse of(String code, Long timestamp, MessageResponse error) {
    ErrorResponse wrapperResponse = new ErrorResponse();
    wrapperResponse.setCode(code);
    wrapperResponse.setTimestamp(timestamp);
    wrapperResponse.setMessage(error);
    return wrapperResponse;
  }
}
