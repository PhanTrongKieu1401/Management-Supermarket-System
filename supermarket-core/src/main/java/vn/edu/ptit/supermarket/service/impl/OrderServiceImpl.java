package vn.edu.ptit.supermarket.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.edu.ptit.supermarket.entity.Order;
import vn.edu.ptit.supermarket.model.response.OrderResponse;
import vn.edu.ptit.supermarket.repository.OrderRepository;
import vn.edu.ptit.supermarket.service.OrderService;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  @Override
  public Order saveOrder(Order order) {
    log.info("(saveOrder)order: {}", order);
    return orderRepository.save(order);
  }

  @Override
  public Order findById(String orderId) {
    log.info("(findById)orderId: {}", orderId);
    return orderRepository.findById(orderId).orElse(null);
  }

  @Override
  public List<OrderResponse> getOrdersByCustomerId(String customerId) {
    log.info("(getOrdersByCustomerId)customerId: {}", customerId);
    return orderRepository.getOrdersByCustomerId(customerId);
  }

  @Override
  public void updatePaymentStatus(String orderId, String paymentStatus) {
    log.info("(updatePaymentStatus)orderId: {}, paymentStatus: {}", orderId, paymentStatus);
    orderRepository.updatePaymentStatus(orderId, paymentStatus);
  }

  @Override
  public void updateOrderStatus(String orderId, String orderStatus) {
    log.info("(updateOrderStatus)orderId: {}, orderStatus: {}", orderId, orderStatus);
    orderRepository.updateOrderStatus(orderId, orderStatus);
  }

  @Override
  public void deleteOrder(String orderId) {
    log.info("(deleteOrder)orderId: {}", orderId);
    orderRepository.deleteById(orderId);
  }
}
