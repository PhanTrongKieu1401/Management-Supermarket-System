package vn.edu.ptit.supermarket.controller;

import static vn.edu.ptit.supermarket.core_authentication.util.SecurityUtil.getMemberId;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.supermarket.core_authentication.model.response.BaseResponse;
import vn.edu.ptit.supermarket.facade.CartFacadeService;
import vn.edu.ptit.supermarket.model.request.ProductInCartRequest;
import vn.edu.ptit.supermarket.model.response.CartResponse;
import vn.edu.ptit.supermarket.model.response.CartUpdateResponse;

@Slf4j
@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@Tag(name = "Cart", description = "Cart API")
public class CartController {

  private final CartFacadeService cartFacadeService;

  @GetMapping("/cart")
  @Operation(summary = "Find cart", description = "Find cart by customer id")
  public BaseResponse<CartResponse> findCartByCustomerId() {
    log.info("(findCartByCustomerId)");
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(), cartFacadeService.findCartByCustomerId(getMemberId()));
  }

  @PutMapping("/cart")
  @Operation(summary = "Update cart", description = "Remove product ordered in cart")
  public BaseResponse<String> updateCart(@Valid @RequestBody List<ProductInCartRequest> productInCartRequests) {
    log.info("(deleteProductInCart)productInCartRequests: {}", productInCartRequests);
    for(ProductInCartRequest productInCartRequest : productInCartRequests) {
      cartFacadeService.deleteProductInCart(getMemberId(), productInCartRequest.getProductId());
    }
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(), "Cập nhật giỏ hàng thành công!");
  }

  @PutMapping("product-in-cart")
  @Operation(summary = "Update cart", description = "Update product in cart")
  public BaseResponse<CartUpdateResponse> updateProductInCart(@Valid @RequestBody ProductInCartRequest productInCartRequest) {
    log.info("(updateProductInCart)productInCartRequest: {}", productInCartRequest);
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(), cartFacadeService.updateProductInCart(getMemberId(), productInCartRequest.getProductId(), productInCartRequest.getQuantity()));
  }

  @DeleteMapping("/product-in-cart/{product-id}")
  @Operation(summary = "Delete product in cart", description = "Delete product in cart")
  public BaseResponse<String> deleteProductInCart(@Valid @PathVariable("product-id") String productId) {
    log.info("(deleteProductInCart)productId: {}", productId);
    cartFacadeService.deleteProductInCart(getMemberId(), productId);
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(), "Xóa sản phẩm khi giỏ hàng thành công!");
  }
}
