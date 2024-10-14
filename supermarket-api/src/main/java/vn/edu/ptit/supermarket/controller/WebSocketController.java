package vn.edu.ptit.supermarket.controller;

import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.supermarket.constant.OrderStatus;
import vn.edu.ptit.supermarket.constant.PaymentStatus;
import vn.edu.ptit.supermarket.core_email.healper.EmailHelper;
import vn.edu.ptit.supermarket.core_redis.constant.CacheConstant;
import vn.edu.ptit.supermarket.core_redis.service.RedisService;
import vn.edu.ptit.supermarket.facade.OrderFacadeService;
import vn.edu.ptit.supermarket.model.request.TransactionStatusRequest;
import vn.edu.ptit.supermarket.model.response.OrderWSResponse;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class WebSocketController {

  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;

  private final OrderFacadeService orderFacadeService;

  private final RedisService redisService;
  private final EmailHelper emailHelper;

  @MessageMapping("/order") // Đường dẫn để nhận tin nhắn
//  @SendTo("/topic/order") // Gửi lại tin nhắn đến tất cả client đăng ký ở /topic/order
  public String processOrder(TransactionStatusRequest transactionStatusRequest) {
    log.info("(processOrder)transactionStatusRequest: {}", transactionStatusRequest);
    if (transactionStatusRequest.getResultCode() == 0) {
      log.info("(processOrder-success)orderId: {}", transactionStatusRequest.getOrderId());
      redisService.save(CacheConstant.ORDER_KEY, transactionStatusRequest.getOrderId(), "SUCCESS");
      orderFacadeService.updatePaymentStatus(transactionStatusRequest.getOrderId(), PaymentStatus.PAID.name());
      orderFacadeService.updateOrderStatus(transactionStatusRequest.getOrderId(), OrderStatus.PENDING.name());
      simpMessagingTemplate.convertAndSendToUser(transactionStatusRequest.getOrderId(),
          "/topic/order", new OrderWSResponse(true, transactionStatusRequest.getOrderId()));
      sendEmailToCustomer(transactionStatusRequest.getOrderId(), "thành công");
    } else {
      log.info("(processOrder-failed)");
      redisService.save(CacheConstant.ORDER_KEY, transactionStatusRequest.getOrderId(), "FAILED");
      orderFacadeService.deleteOrder(transactionStatusRequest.getOrderId());
      simpMessagingTemplate.convertAndSendToUser(transactionStatusRequest.getOrderId(),
          "/topic/order", new OrderWSResponse(false, transactionStatusRequest.getOrderId()));
      sendEmailToCustomer(transactionStatusRequest.getOrderId(), "thất bại");
    }
    return "Message received: " + transactionStatusRequest;
  }

  private void sendEmailToCustomer(String orderId, String status) {
    String subject = "Notify for customer order " + orderId;
    String email = orderFacadeService.getEmailCustomer(orderId);
    var param = new HashMap<String, Object>();
    param.put("order_id", orderId);
    param.put("status", status);
    emailHelper.send(subject, email, "Notify-payment-success-template", param);
  }
}
