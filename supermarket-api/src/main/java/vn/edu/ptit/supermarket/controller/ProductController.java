package vn.edu.ptit.supermarket.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.supermarket.core_authentication.model.response.BaseResponse;
import vn.edu.ptit.supermarket.model.response.ProductPageResponse;
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
      @RequestParam(name = "keySearch", required = false, defaultValue = "") String keySearch,
      @RequestParam(name = "type", required = true, defaultValue = "ALL") String type,
      @RequestParam(name = "sortType", required = false, defaultValue = "ASC") String sortType,
      @RequestParam(name = "keySort", required = false, defaultValue = "name") String keySort,
      @RequestParam(name = "page", required = true, defaultValue = "1") int page,
      @RequestParam(name = "size", required = true, defaultValue = "20") int size
  ) {
    log.info("findAllProductForHomeCustomer: keySearch: {}, type: {}, sortType: {}, keySort: {}, page: {}, size: {}", keySearch, type, sortType, keySort, page, size);
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(), productService.findAllProductForCustomer(keySearch, type, sortType, keySort, page, size));
  }
}
