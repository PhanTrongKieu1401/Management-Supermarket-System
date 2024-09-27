package vn.edu.ptit.supermarket.core_authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.ptit.supermarket.core_authentication.entity.Address;

public interface AddressRepository extends JpaRepository<Address, String> {

}
