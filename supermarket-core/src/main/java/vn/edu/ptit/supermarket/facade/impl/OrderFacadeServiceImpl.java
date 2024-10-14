package vn.edu.ptit.supermarket.facade.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.ptit.supermarket.core_authentication.constant.Role;
import vn.edu.ptit.supermarket.core_email.healper.EmailHelper;
import vn.edu.ptit.supermarket.core_redis.constant.CacheConstant;
import vn.edu.ptit.supermarket.core_redis.service.RedisService;
import vn.edu.ptit.supermarket.entity.Order;
import vn.edu.ptit.supermarket.entity.Product;
import vn.edu.ptit.supermarket.exception.ProductNotEnoughException;
import vn.edu.ptit.supermarket.facade.OrderFacadeService;
import vn.edu.ptit.supermarket.mapper.OrderMapper;
import vn.edu.ptit.supermarket.mapper.ProductInOrderMapper;
import vn.edu.ptit.supermarket.model.request.CustomerOrderRequest;
import vn.edu.ptit.supermarket.model.request.ProductOrderRequest;
import vn.edu.ptit.supermarket.model.response.CashierInOrderResponse;
import vn.edu.ptit.supermarket.model.response.CustomerInOrderResponse;
import vn.edu.ptit.supermarket.model.response.OrderDetailResponse;
import vn.edu.ptit.supermarket.model.response.OrderResponse;
import vn.edu.ptit.supermarket.model.response.ProductInOrderResponse;
import vn.edu.ptit.supermarket.model.response.ReceiverResponse;
import vn.edu.ptit.supermarket.model.response.VoucherInOrderResponse;
import vn.edu.ptit.supermarket.service.StaffService;
import vn.edu.ptit.supermarket.service.CustomerService;
import vn.edu.ptit.supermarket.service.OrderService;
import vn.edu.ptit.supermarket.service.ProductInOrderService;
import vn.edu.ptit.supermarket.service.ProductService;
import vn.edu.ptit.supermarket.service.ReceiverService;
import vn.edu.ptit.supermarket.service.VoucherService;

@Slf4j
@RequiredArgsConstructor
public class OrderFacadeServiceImpl implements OrderFacadeService {

  private final OrderService orderService;
  private final ProductService productService;
  private final ProductInOrderService productInOrderService;
  private final ReceiverService receiverService;
  private final VoucherService voucherService;
  private final StaffService staffService;
  private final CustomerService customerService;
  private final RedisService redisService;
  private final EmailHelper emailHelper;
  private final OrderMapper orderMapper;
  private final ProductInOrderMapper productInOrderMapper;

  @Override
  public void updatePaymentStatus(String orderId, String paymentStatus) {
    log.info("(updatePaymentStatus)orderId: {}, paymentStatus: {}", orderId, paymentStatus);
    orderService.updatePaymentStatus(orderId, paymentStatus);
  }

  @Override
  public void updateOrderStatus(String orderId, String orderStatus) {
    log.info("(updateOrderStatus)orderId: {}, orderStatus: {}", orderId, orderStatus);
    orderService.updateOrderStatus(orderId, orderStatus);
  }

  @Override
  @Transactional
  public synchronized String replaceOrder(CustomerOrderRequest customerOrderRequest) {
    log.info("(replaceOrder)orderRequest: {}", customerOrderRequest);
    for(ProductOrderRequest productOrderRequest : customerOrderRequest.getOrderRequest().getProducts()) {
      log.info("(replaceOrder)productOrderRequest: {}", productOrderRequest);
      Product product = productService.findProductById(productOrderRequest.getProductId());
      if(product.getQuantityInStock() < productOrderRequest.getQuantity()) {
        throw new ProductNotEnoughException(product.getName());
      }
      product.setQuantityInStock(product.getQuantityInStock() - productOrderRequest.getQuantity());
      productService.saveProduct(product);
    }

    Order order = orderMapper.mapToOrder(customerOrderRequest);
    orderService.saveOrder(order);
    for(ProductOrderRequest productOrderRequest : customerOrderRequest.getOrderRequest().getProducts()) {
      log.info("(replaceOrder)(saveProductInOrder)productOrderRequest: {}", productOrderRequest);
      BigDecimal priceImport = productService.findPriceImportByProductId(productOrderRequest.getProductId());
      productInOrderService.saveProductInOrder(productInOrderMapper.mapToProductInOrder(productOrderRequest, order.getId(), priceImport));
    }
    receiverService.saveReceiver(order.getId(),customerOrderRequest.getOrderRequest().getReceiver());

    String subject = "Notify for customer order " + order.getId();
    var param = new HashMap<String, Object>();
    param.put("order_id", order.getId());
    emailHelper.send(subject, customerService.findCustomerById(order.getCustomerId()).getEmail(), "Notify-order-template", param);

    return order.getId();
  }

  @Override
  public List<OrderResponse> getOrdersByCustomerId(String customerId) {
    log.info("(getOrdersByCustomerId)customerId: {}", customerId);
    return orderService.getOrdersByCustomerId(customerId);
  }

  @Override
  public OrderDetailResponse getOrderDetail(String orderId) {
    log.info("(getOrderDetail)orderId: {}", orderId);
    Order order = orderService.findById(orderId);
    List<ProductInOrderResponse> productInOrderResponses = productInOrderService.findAllProductInOrderByOrderId(orderId);

    ReceiverResponse receiverResponse = receiverService.getReceiverByOrderId(orderId);

    VoucherInOrderResponse voucherInOrderResponse = null;
    if(order.getVoucherId() != null) {
      voucherInOrderResponse = voucherService.findVoucherById(order.getVoucherId());

    }

    CashierInOrderResponse cashierInOrderResponse = null;
    if(order.getStaffId() != null) {
      cashierInOrderResponse = staffService.findCashierById(order.getStaffId(), Role.CASHIER.toString());
    }

    CustomerInOrderResponse customerInOrderResponse = null;
    if(order.getCustomerId() != null) {
      customerInOrderResponse = customerService.findCustomerById(order.getCustomerId());
    }

    return orderMapper.mapToOrderDetail(order, productInOrderResponses, voucherInOrderResponse, receiverResponse, cashierInOrderResponse, customerInOrderResponse);
  }

  @Override
  public String getEmailCustomer(String orderId) {
    log.info("(getEmailCustomer)orderId: {}", orderId);
    return customerService.findCustomerById(orderService.findById(orderId).getCustomerId()).getEmail();
  }

  @Override
  public String getPaymentStatus(String orderId) {
    log.info("(getPaymentStatus)orderId: {}", orderId);
    var status = redisService.get(CacheConstant.ORDER_KEY, orderId);
    return status.get().toString();
  }

  @Override
  @Transactional
  public void deleteOrder(String orderId) {
    log.info("(deleteOrder)orderId: {}", orderId);

    List<ProductInOrderResponse> productInOrderResponses = productInOrderService.findAllProductInOrderByOrderId(orderId);
    for(ProductInOrderResponse productInOrderResponse : productInOrderResponses) {
      Product product = productService.findProductById(productInOrderResponse.getProductId());
      product.setQuantityInStock(product.getQuantityInStock() + productInOrderResponse.getQuantity());
      productService.saveProduct(product);
    }

    receiverService.deleteReceiverByOrderId(orderId);
    productInOrderService.deleteProductInOrderByOrderId(orderId);
    orderService.deleteOrder(orderId);
  }
}
