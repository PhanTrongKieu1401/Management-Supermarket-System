package vn.edu.ptit.supermarket.facade;

import vn.edu.ptit.supermarket.model.response.CartResponse;
import vn.edu.ptit.supermarket.model.response.CartUpdateResponse;

public interface CartFacadeService {

  CartResponse findCartByCustomerId(String customerId);

  CartUpdateResponse updateProductInCart(String customerId, String productId, int quantity);

  void deleteProductInCart(String customerId, String productId);
}
