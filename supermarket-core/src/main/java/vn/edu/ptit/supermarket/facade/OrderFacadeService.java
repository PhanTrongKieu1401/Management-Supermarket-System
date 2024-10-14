package vn.edu.ptit.supermarket.facade;

import java.util.List;
import vn.edu.ptit.supermarket.model.request.CustomerOrderRequest;
import vn.edu.ptit.supermarket.model.response.OrderDetailResponse;
import vn.edu.ptit.supermarket.model.response.OrderResponse;

public interface OrderFacadeService {

  void updatePaymentStatus(String orderId, String paymentStatus);

  void updateOrderStatus(String orderId, String orderStatus);

  String replaceOrder(CustomerOrderRequest customerOrderRequest);

  List<OrderResponse> getOrdersByCustomerId(String customerId);
  OrderDetailResponse getOrderDetail(String orderId);
  String getEmailCustomer(String orderId);
  String getPaymentStatus(String orderId);

  void deleteOrder(String orderId);
}
