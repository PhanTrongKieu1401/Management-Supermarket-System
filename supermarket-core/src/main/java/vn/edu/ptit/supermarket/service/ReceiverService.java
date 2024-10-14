package vn.edu.ptit.supermarket.service;

import vn.edu.ptit.supermarket.model.request.ReceiverOrderRequest;
import vn.edu.ptit.supermarket.model.response.ReceiverResponse;

public interface ReceiverService {

  ReceiverResponse getReceiverByOrderId(String orderId);

  void saveReceiver(String orderId, ReceiverOrderRequest receiverOrderRequest);
  void deleteReceiverByOrderId(String orderId);
}
