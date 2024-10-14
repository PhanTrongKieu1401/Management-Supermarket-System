package vn.edu.ptit.supermarket.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.edu.ptit.supermarket.model.response.CustomerInOrderResponse;
import vn.edu.ptit.supermarket.repository.CustomerRepository;
import vn.edu.ptit.supermarket.service.CustomerService;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  @Override
  public CustomerInOrderResponse findCustomerById(String customerId) {
    log.info("(findCustomerById)customerId: {}", customerId);
    return customerRepository.findCustomerById(customerId);
  }
}
