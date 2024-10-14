package vn.edu.ptit.supermarket.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.edu.ptit.supermarket.entity.Receiver;
import vn.edu.ptit.supermarket.model.request.ReceiverOrderRequest;
import vn.edu.ptit.supermarket.model.response.ReceiverResponse;
import vn.edu.ptit.supermarket.repository.ReceiverRepository;
import vn.edu.ptit.supermarket.service.ReceiverService;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReceiverServiceImpl implements ReceiverService {

  private final ReceiverRepository receiverRepository;

  @Override
  public ReceiverResponse getReceiverByOrderId(String orderId) {
    log.info("(getReceiverByOrderId)orderId: {}", orderId);
    return receiverRepository.getReceiverByOrderId(orderId);
  }

  @Override
  @Transactional
  public void saveReceiver(String orderId, ReceiverOrderRequest receiverOrderRequest) {
    log.info("(saveReceiver)orderId: {},receiverOrderRequest: {}", orderId, receiverOrderRequest);
    receiverRepository.save(new Receiver(
        receiverOrderRequest.getFullName(),
        receiverOrderRequest.getPhone(),
        receiverOrderRequest.getAddressDetail(),
        receiverOrderRequest.getWard(),
        receiverOrderRequest.getDistrict(),
        receiverOrderRequest.getProvince(),
        orderId
    ));
  }

  @Override
  @Transactional
  public void deleteReceiverByOrderId(String orderId) {
    log.info("(deleteReceiverByOrderId)orderId: {}", orderId);
    receiverRepository.deleteByOrderId(orderId);
  }
}
