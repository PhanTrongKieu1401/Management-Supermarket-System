package vn.edu.ptit.supermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.supermarket.entity.Customer;
import vn.edu.ptit.supermarket.model.response.CustomerInOrderResponse;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

  @Query("""
      select new vn.edu.ptit.supermarket.model.response.CustomerInOrderResponse(c.firstName, c.middleName, c.lastName, c.email, c.phone)
      from Customer c where c.id = :customerId
      """)
  CustomerInOrderResponse findCustomerById(String customerId);
}
