package vn.edu.ptit.supermarket.service;

import java.util.List;
import vn.edu.ptit.supermarket.entity.ProductInOrder;
import vn.edu.ptit.supermarket.model.response.ProductInOrderResponse;

public interface ProductInOrderService {

  void saveProductInOrder(ProductInOrder productInOrder);

  List<ProductInOrderResponse> findAllProductInOrderByOrderId(String orderId);

  void deleteProductInOrderByOrderId(String orderId);
}
