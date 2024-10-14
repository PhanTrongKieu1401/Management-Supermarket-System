package vn.edu.ptit.supermarket.controller;

import static vn.edu.ptit.supermarket.core_authentication.util.SecurityUtil.getMemberId;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.supermarket.core_kafka.constant.Topic;
import vn.edu.ptit.supermarket.core_authentication.model.response.BaseResponse;
import vn.edu.ptit.supermarket.facade.OrderFacadeService;
import vn.edu.ptit.supermarket.model.request.CancelOrderRequest;
import vn.edu.ptit.supermarket.model.request.CustomerOrderRequest;
import vn.edu.ptit.supermarket.model.request.OrderRequest;
import vn.edu.ptit.supermarket.model.response.OrderDetailResponse;
import vn.edu.ptit.supermarket.model.response.OrderResponse;

@Slf4j
@RestController
@RequestMapping("/api/v1/customer/orders")
@RequiredArgsConstructor
@Tag(name = "Order", description = "Order API")
public class OrderController {

  private final OrderFacadeService orderFacadeService;
  private final KafkaTemplate<String, String> kafkaTemplate;

  @PostMapping()
  @Operation(summary = "Replace order", description = "Check order, chuyển hướng trang thanh toán")
  public BaseResponse<String> replaceOrder(@RequestBody @Valid OrderRequest orderRequest, HttpServletRequest request) {
    log.info("(replaceOrder)orderRequest: {}", orderRequest);
    String authorId = request.getHeader("Authorization").substring(7);

    CustomerOrderRequest customerOrderRequest = new CustomerOrderRequest(authorId, getMemberId(), orderRequest);
    String orderJson = convertOrderToJson(customerOrderRequest);
    CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(Topic.TOPIC_ORDER, orderJson);
    future.handle((result, ex) -> {
      if (ex != null) {
        log.error("Failed to send order to Kafka", ex);
      } else {
        log.info("Order sent to Kafka successfully: {}", result.getProducerRecord().value());
      }
      return null;
    });
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(), "Đơn hàng đang được xử lý");
  }

  @GetMapping
  @Operation(summary = "Get orders", description = "Get orders")
  public BaseResponse<List<OrderResponse>> getOrdersByCustomerId() {
    log.info("(getOrdersByCustomerId)");
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(), orderFacadeService.getOrdersByCustomerId(getMemberId()));
  }

  @GetMapping("/{order-id}")
  @Operation(summary = "Get order", description = "Get order detail")
  public BaseResponse<OrderDetailResponse> getOrderDetail(@PathVariable("order-id") String orderId) {
    log.info("(getOrderDetail)orderId: {}", orderId);
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(), orderFacadeService.getOrderDetail(orderId));
  }

  @GetMapping("/payment-status/{order-id}")
  @Operation(summary = "Get payment status", description = "Get payment status")
  public BaseResponse<String> getPaymentStatus(@PathVariable("order-id") String orderId) {
    log.info("(getPaymentStatus)orderId: {}", orderId);
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(), orderFacadeService.getPaymentStatus(orderId));
  }

  @DeleteMapping("/{order-id}")
  @Operation(summary = "Delete order", description = "Delete order")
  public BaseResponse<String> deleteOrder(@PathVariable("order-id") String orderId, HttpServletRequest request) {
    log.info("(deleteOrder)orderId: {}", orderId);

    String authorId = request.getHeader("Authorization").substring(7);
    CancelOrderRequest cancelOrderRequest = new CancelOrderRequest(authorId, orderId);

    String orderJson = convertCancelOrderToJson(cancelOrderRequest);

    CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(Topic.TOPIC_CANCEL_ORDER, orderJson);
    future.handle((result, ex) -> {
      if (ex != null) {
        log.error("Failed to send order to Kafka", ex);
      } else {
        log.info("Order sent to Kafka successfully: {}", result.getProducerRecord().value());
      }
      return null;
    });
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(), "Đang hủy đơn!");
  }

  private String convertOrderToJson(CustomerOrderRequest customerOrderRequest) {
    log.info("(convertOrderToJson)orderRequest: {}", customerOrderRequest);
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(customerOrderRequest);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return "{}";
    }
  }

  private String convertCancelOrderToJson(CancelOrderRequest cancelOrderRequest) {
    log.info("(convertCancelOrderToJson)cancelOrderRequest: {}", cancelOrderRequest);
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(cancelOrderRequest);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return "{}";
    }
  }
}
