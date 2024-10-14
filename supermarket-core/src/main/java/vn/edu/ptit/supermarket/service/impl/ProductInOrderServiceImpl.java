package vn.edu.ptit.supermarket.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.edu.ptit.supermarket.entity.ProductInOrder;
import vn.edu.ptit.supermarket.model.response.ProductInOrderResponse;
import vn.edu.ptit.supermarket.repository.ProductInOrderRepository;
import vn.edu.ptit.supermarket.service.ProductInOrderService;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductInOrderServiceImpl implements ProductInOrderService {

  private final ProductInOrderRepository productInOrderRepository;

  @Override
  public void saveProductInOrder(ProductInOrder productInOrder) {
    log.info("(saveProductInOrder)productInOrder: {}", productInOrder);
    productInOrderRepository.save(productInOrder);
  }

  @Override
  public List<ProductInOrderResponse> findAllProductInOrderByOrderId(String orderId) {
    log.info("(findAllProductInOrderByOrderId)orderId: {}", orderId);
    return productInOrderRepository.findAllProductInOrderByOrderId(orderId);
  }

  @Override
  public void deleteProductInOrderByOrderId(String orderId) {
    log.info("(deleteProductInOrderByOrderId)orderId: {}", orderId);
    productInOrderRepository.deleteByOrderId(orderId);
  }
}
