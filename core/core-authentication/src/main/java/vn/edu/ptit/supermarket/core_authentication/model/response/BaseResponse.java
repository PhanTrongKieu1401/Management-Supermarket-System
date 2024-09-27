package vn.edu.ptit.supermarket.core_authentication.model.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class BaseResponse<T> {

  private int status;
  private String timestamp;
  private T data;

  public BaseResponse of(int status, T data) {
    return BaseResponse.of(status, String.valueOf(LocalDateTime.now()), data);
  }

  public static BaseResponse of(int status) {
    return BaseResponse.of(status, String.valueOf(LocalDateTime.now()), null);
  }
}
