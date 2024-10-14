package vn.edu.ptit.supermarket.facade.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.ptit.supermarket.entity.Product;
import vn.edu.ptit.supermarket.exception.ProductNotEnoughException;
import vn.edu.ptit.supermarket.facade.CartFacadeService;
import vn.edu.ptit.supermarket.model.response.CartResponse;
import vn.edu.ptit.supermarket.model.response.CartUpdateResponse;
import vn.edu.ptit.supermarket.model.response.ProductInCartResponse;
import vn.edu.ptit.supermarket.service.ProductInCartService;
import vn.edu.ptit.supermarket.service.ProductService;

@Slf4j
@RequiredArgsConstructor
public class CartFacadeServiceImpl implements CartFacadeService {

  private final ProductService productService;
  private final ProductInCartService productInCartService;

  @Override
  public CartResponse findCartByCustomerId(String customerId) {
    log.info("(findCartByCustomerId)customerId: {}", customerId);
    return new CartResponse(productInCartService.countProductInCartByCartId(customerId),
        productInCartService.findProductInCartByCartId(customerId));
  }

  @Override
  public CartUpdateResponse updateProductInCart(String customerId, String productId, int quantity) {
    log.info("(updateProductInCart)customerId: {}, productInCartId: {}, quantity: {}", customerId,
        productId, quantity);
    ProductInCartResponse productInCartResponse = productInCartService.findProductInCartByProductIdAndCartId(
        productId, customerId);
    String message;
    if (productInCartResponse == null) {
      productInCartService.addProductInCart(customerId, productId, quantity);
      productInCartResponse = productInCartService.findProductInCartByProductIdAndCartId(productId,
          customerId);
      message = "Thêm sản phẩm vào giỏ hàng thành công!";
    } else {
      Product product = productService.findProductById(productInCartResponse.getProductId());
      if (quantity <= product.getQuantityInStock()) {
        productInCartService.updateProductInCart(customerId, productId, quantity);
        productInCartResponse.setQuantity(quantity);
        productInCartResponse.setPriceSell(productInCartResponse.getPriceSell());
        productInCartResponse.setDiscountSell(productInCartResponse.getDiscountSell());
        productInCartResponse.setQuantityInStock(productInCartResponse.getQuantityInStock() - quantity);
        message = "Cập nhật sản phẩm trong giỏ hàng thành công!";
      } else {
        throw new ProductNotEnoughException(productInCartResponse.getName());
      }
    }
    return new CartUpdateResponse(message, productInCartResponse);
  }

  @Override
  public void deleteProductInCart(String customerId, String productId) {
    log.info("(deleteProductInCart)customerId: {}, productId: {}", customerId, productId);
    productInCartService.removeProductInCart(customerId, productId);
  }
}
