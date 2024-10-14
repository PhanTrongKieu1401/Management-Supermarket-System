package vn.edu.ptit.supermarket.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import vn.edu.ptit.supermarket.core_kafka.constant.Topic;

import vn.edu.ptit.supermarket.facade.OrderFacadeService;
import vn.edu.ptit.supermarket.model.request.CancelOrderRequest;
import vn.edu.ptit.supermarket.model.request.CustomerOrderRequest;
import vn.edu.ptit.supermarket.model.response.OrderWSResponse;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderConsumerService {

  private final OrderFacadeService orderFacadeService;

  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;

  @KafkaListener(topics = Topic.TOPIC_ORDER, groupId = "supermarket")
  public void consumeOrder(String orderJson, Acknowledgment acknowledgment) {
    log.info("(consumeOrder)orderJson: {}", orderJson);
    CustomerOrderRequest customerOrderRequest = convertJsonToOrder(orderJson);
    String authorId = customerOrderRequest.getAuthorId();
    try {
      String newOrderId = orderFacadeService.replaceOrder(customerOrderRequest);
      acknowledgment.acknowledge();
      OrderWSResponse orderWSResponse;
      if(newOrderId != null) {
        log.info("(consumeOrder)newOrderId: {}", newOrderId);
        orderWSResponse = new OrderWSResponse(true, newOrderId);
      } else {
        log.info("(consumeOrder)newOrderId: null");
        orderWSResponse = new OrderWSResponse(false, null);
      }
      simpMessagingTemplate.convertAndSendToUser(authorId, "/topic/order", orderWSResponse);
    } catch (Exception e) {
      acknowledgment.acknowledge();
      OrderWSResponse orderWSResponse = new OrderWSResponse(false, null);
      simpMessagingTemplate.convertAndSendToUser(authorId, "/topic/order", orderWSResponse);
    }
  }

  @KafkaListener(topics = Topic.TOPIC_CANCEL_ORDER, groupId = "supermarket")
  public void consumeCancelOrder(String orderJson, Acknowledgment acknowledgment) {
    log.info("(consumeCancelOrder)orderJson: {}", orderJson);
    CancelOrderRequest cancelOrderRequest = convertJsonToCancelOrder(orderJson);
    String authorId = cancelOrderRequest.getAuthorId();
    String orderId = cancelOrderRequest.getOrderId();
    try {
      orderFacadeService.deleteOrder(orderId);
      acknowledgment.acknowledge();
      simpMessagingTemplate.convertAndSendToUser(authorId, "/topic/cancel-order", true);
    } catch (Exception e) {
      acknowledgment.acknowledge();
      simpMessagingTemplate.convertAndSendToUser(authorId, "/topic/cancel-order", false);
    }
  }

  private CustomerOrderRequest convertJsonToOrder(String orderJson) {
    log.info("(convertJsonToOrder)orderJson: {}", orderJson);
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readValue(orderJson, CustomerOrderRequest.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }

  private CancelOrderRequest convertJsonToCancelOrder(String orderJson) {
    log.info("(convertJsonToCancelOrder)orderJson: {}", orderJson);
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readValue(orderJson, CancelOrderRequest.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }
}
