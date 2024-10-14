package vn.edu.ptit.supermarket.service;

import java.util.List;
import vn.edu.ptit.supermarket.entity.Order;
import vn.edu.ptit.supermarket.model.response.OrderResponse;

public interface OrderService {

  Order saveOrder(Order order);

  Order findById(String orderId);

  List<OrderResponse> getOrdersByCustomerId(String customerId);

  void updatePaymentStatus(String orderId, String paymentStatus);

  void updateOrderStatus(String orderId, String orderStatus);

  void deleteOrder(String orderId);
}
