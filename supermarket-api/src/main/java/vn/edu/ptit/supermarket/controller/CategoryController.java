package vn.edu.ptit.supermarket.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.supermarket.core_authentication.model.response.BaseResponse;
import vn.edu.ptit.supermarket.entity.Category;
import vn.edu.ptit.supermarket.service.CategoryService;

@Slf4j
@RestController
@RequestMapping("/api/v1/public/category")
@RequiredArgsConstructor
@Tag(name = "Category", description = "Category API")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping()
  @Operation(summary = "Find all", description = "Find all category")
  public BaseResponse<List<Category>> findAll() {
    log.info("findAll");
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(), categoryService.findAll());
  }
}
