package vn.edu.ptit.supermarket.service.impl;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.edu.ptit.supermarket.entity.ProductInCart;
import vn.edu.ptit.supermarket.model.response.ProductInCartResponse;
import vn.edu.ptit.supermarket.repository.ProductInCartRepository;
import vn.edu.ptit.supermarket.service.ProductInCartService;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductInCartServiceImpl implements ProductInCartService {

  private final ProductInCartRepository productInCartRepository;

  @Override
  public int countProductInCartByCartId(String cartId) {
    log.info("(countProductInCartByCartId)cartId: {}", cartId);
    return productInCartRepository.countProductInCartByCartId(cartId);
  }

  @Override
  public List<ProductInCartResponse> findProductInCartByCartId(String cartId) {
    log.info("(findProductInCartByCartId)cartId: {}", cartId);
    return productInCartRepository.findProductInCartsByCartId(cartId);
  }

  @Override
  public ProductInCartResponse findProductInCartByProductIdAndCartId(String productId, String cartId) {
    log.info("(findProductInCartByIdAndCartId)productId: {}, cartId: {}", productId, cartId);
    return productInCartRepository.findProductInCartByProductIdAndCartId(productId, cartId);
  }

  @Override
  @Transactional
  public void addProductInCart(String cartId, String productId, int quantity) {
    log.info("(addProductInCart)cartId: {}, productId: {}, quantity: {}", cartId, productId, quantity);
    ProductInCart productInCart = new ProductInCart();
    productInCart.setId("PIC" + System.currentTimeMillis() + (int) (Math.random() * 1000));
    productInCart.setCartId(cartId);
    productInCart.setProductId(productId);
    productInCart.setQuantity(quantity);
    productInCartRepository.save(productInCart);
  }

  @Override
  @Transactional
  public void updateProductInCart(String cartId, String productId, int quantity) {
    log.info("(updateProductInCart)cartId: {}, productId: {}, quantity: {}", cartId, productId, quantity);
    productInCartRepository.updateProductInCart(cartId, productId, quantity);
  }

  @Override
  @Transactional
  public void removeProductInCart(String cartId, String productId) {
    log.info("(removeProductInCart)cartId: {}, productId: {}", cartId, productId);
    productInCartRepository.removeProductInCart(cartId, productId);
  }
}
