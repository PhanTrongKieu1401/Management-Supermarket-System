package vn.edu.ptit.supermarket.mapper.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Component;
import vn.edu.ptit.supermarket.entity.Order;
import vn.edu.ptit.supermarket.mapper.OrderMapper;
import vn.edu.ptit.supermarket.model.request.CustomerOrderRequest;
import vn.edu.ptit.supermarket.model.response.CashierInOrderResponse;
import vn.edu.ptit.supermarket.model.response.CustomerInOrderResponse;
import vn.edu.ptit.supermarket.model.response.OrderDetailResponse;
import vn.edu.ptit.supermarket.model.response.ProductInOrderResponse;
import vn.edu.ptit.supermarket.model.response.ReceiverResponse;
import vn.edu.ptit.supermarket.model.response.VoucherInOrderResponse;

@Component
public class OrderMapperImpl implements OrderMapper {

  @Override
  public Order mapToOrder(CustomerOrderRequest customerOrderRequest) {
    return new Order(
        customerOrderRequest.getOrderRequest().getPaymentMethod() + System.currentTimeMillis(),
        customerOrderRequest.getOrderRequest().getPaymentMethod(),
        LocalDateTime.now(),
        customerOrderRequest.getOrderRequest().getPaymentStatus(),
        customerOrderRequest.getOrderRequest().getOrderStatus(),
        customerOrderRequest.getOrderRequest().getTransportFee(),
        customerOrderRequest.getCustomerId(),
        null,
        customerOrderRequest.getOrderRequest().getVoucher() != null ?
            customerOrderRequest.getOrderRequest().getVoucher().getVoucherId() : null
    );
  }

  @Override
  public OrderDetailResponse mapToOrderDetail(Order order,
      List<ProductInOrderResponse> productInOrderResponses,
      VoucherInOrderResponse voucherInOrderResponse, ReceiverResponse receiverInOrderResponse,
      CashierInOrderResponse cashierInOrderResponse,
      CustomerInOrderResponse customerInOrderResponse) {

    BigDecimal totalAmount = BigDecimal.ZERO;
    int quantity = 0;
    BigDecimal totalPriceReduced = BigDecimal.ZERO;
    for (ProductInOrderResponse productInOrderResponse : productInOrderResponses) {
      quantity += productInOrderResponse.getQuantity();
      if (productInOrderResponse.getDiscountSell().compareTo(BigDecimal.ZERO) > 0) {
        totalPriceReduced = totalPriceReduced.add((productInOrderResponse.getPriceSell()
            .subtract(productInOrderResponse.getDiscountSell())).multiply(
            BigDecimal.valueOf(productInOrderResponse.getQuantity())));

        totalAmount = totalAmount.add(productInOrderResponse.getDiscountSell()
            .multiply(BigDecimal.valueOf(productInOrderResponse.getQuantity())));
      } else {
        totalAmount = totalAmount.add(productInOrderResponse.getPriceSell()
            .multiply(BigDecimal.valueOf(productInOrderResponse.getQuantity())));
      }
    }

    totalAmount = totalAmount.add(order.getTransportFee());
    if (voucherInOrderResponse != null) {
      totalAmount = totalAmount.subtract(BigDecimal.valueOf(voucherInOrderResponse.getValue()));
    }

    return new OrderDetailResponse(
        order.getId(),
        order.getPaymentMethod(),
        order.getPaymentStatus(),
        order.getPaymentDate().toString(),
        order.getOrderStatus(),
        quantity,
        totalPriceReduced,
        voucherInOrderResponse != null ? voucherInOrderResponse.getValue() : 0f,
        order.getTransportFee(),
        totalAmount,
        productInOrderResponses,
        voucherInOrderResponse,
        receiverInOrderResponse,
        cashierInOrderResponse,
        customerInOrderResponse
    );
  }
}
