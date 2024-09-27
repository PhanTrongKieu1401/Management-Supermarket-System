package vn.edu.ptit.supermarket.core_authentication.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.ptit.supermarket.core_authentication.entity.Address;
import vn.edu.ptit.supermarket.core_authentication.repository.AddressRepository;
import vn.edu.ptit.supermarket.core_authentication.service.AddressService;

@Slf4j
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

  private final AddressRepository addressRepository;
  @Override
  public Address create(Address address) {
    log.info("(create)address: {}", address);
    return addressRepository.save(address);
  }
}
