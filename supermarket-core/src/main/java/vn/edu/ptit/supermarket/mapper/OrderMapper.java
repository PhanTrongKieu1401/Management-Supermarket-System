package vn.edu.ptit.supermarket.mapper;

import java.util.List;
import vn.edu.ptit.supermarket.entity.Order;
import vn.edu.ptit.supermarket.model.request.CustomerOrderRequest;
import vn.edu.ptit.supermarket.model.response.CashierInOrderResponse;
import vn.edu.ptit.supermarket.model.response.CustomerInOrderResponse;
import vn.edu.ptit.supermarket.model.response.OrderDetailResponse;
import vn.edu.ptit.supermarket.model.response.ProductInOrderResponse;
import vn.edu.ptit.supermarket.model.response.ReceiverResponse;
import vn.edu.ptit.supermarket.model.response.VoucherInOrderResponse;

public interface OrderMapper {

  Order mapToOrder(CustomerOrderRequest customerOrderRequest);

  OrderDetailResponse mapToOrderDetail(Order order,
      List<ProductInOrderResponse> productInOrderResponses,
      VoucherInOrderResponse voucherInOrderResponse, ReceiverResponse receiverInOrderResponse,
      CashierInOrderResponse cashierInOrderResponse,
      CustomerInOrderResponse customerInOrderResponse);
}
