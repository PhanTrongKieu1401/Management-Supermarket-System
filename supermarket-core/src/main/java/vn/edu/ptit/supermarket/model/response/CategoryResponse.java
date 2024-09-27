package vn.edu.ptit.supermarket.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class CategoryResponse {

  private String id;
  private String name;

  public CategoryResponse(String id, String name) {
    this.id = id;
    this.name = name;
  }
}
