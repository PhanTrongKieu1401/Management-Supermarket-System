package vn.edu.ptit.supermarket.service;

import java.util.List;
import vn.edu.ptit.supermarket.model.response.ProductInCartResponse;

public interface ProductInCartService {

  int countProductInCartByCartId(String cartId);

  List<ProductInCartResponse> findProductInCartByCartId(String cartId);

  ProductInCartResponse findProductInCartByProductIdAndCartId(String id, String cartId);

  void addProductInCart(String cartId, String productId, int quantity);

  void updateProductInCart(String cartId, String productId, int quantity);

  void removeProductInCart(String cartId, String productId);
}
