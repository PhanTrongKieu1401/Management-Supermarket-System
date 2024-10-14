package vn.edu.ptit.supermarket.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.edu.ptit.supermarket.model.response.VoucherInOrderResponse;
import vn.edu.ptit.supermarket.repository.VoucherRepository;
import vn.edu.ptit.supermarket.service.VoucherService;

@Slf4j
@RequiredArgsConstructor
@Component
public class VoucherServiceImpl implements VoucherService {

  private final VoucherRepository voucherRepository;

  @Override
  public VoucherInOrderResponse findVoucherById(String voucherId) {
    log.info("(findById)voucherId: {}", voucherId);
    return voucherRepository.findVoucherById(voucherId);
  }
}
