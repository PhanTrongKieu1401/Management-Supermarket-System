package vn.edu.ptit.supermarket.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.supermarket.core_authentication.model.response.BaseResponse;
import vn.edu.ptit.supermarket.model.response.ProductPageResponse;
import vn.edu.ptit.supermarket.model.response.ProductResponse;
import vn.edu.ptit.supermarket.service.ProductService;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Product", description = "Product API")
public class ProductController {

  private final ProductService productService;

  @GetMapping("/public/customer/products")
  @Operation(summary = "Find all", description = "Find all product for customer")
  public BaseResponse<ProductPageResponse> findAllProductForCustomer(
      @RequestParam(name = "page", required = true, defaultValue = "1") int page,
      @RequestParam(name = "size", required = true, defaultValue = "20") int size
  ) {
    log.info("findAllProductForHomeCustomer: page {}, size {}", page, size);
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(), productService.findAllProductForCustomer(page, size));
  }
}
