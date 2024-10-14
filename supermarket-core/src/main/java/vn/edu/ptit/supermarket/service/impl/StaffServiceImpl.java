package vn.edu.ptit.supermarket.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.edu.ptit.supermarket.model.response.CashierInOrderResponse;
import vn.edu.ptit.supermarket.repository.StaffRepository;
import vn.edu.ptit.supermarket.service.StaffService;

@Slf4j
@RequiredArgsConstructor
@Component
public class StaffServiceImpl implements StaffService {

  private final StaffRepository staffRepository;

  @Override
  public CashierInOrderResponse findCashierById(String staffId, String role) {
    log.info("(findCashierById)staffId: {}, role: {}", staffId, role);
    return staffRepository.findCashierById(staffId, role);
  }
}
