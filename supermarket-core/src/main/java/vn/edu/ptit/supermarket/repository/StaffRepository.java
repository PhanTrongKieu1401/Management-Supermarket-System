package vn.edu.ptit.supermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.supermarket.entity.Staff;
import vn.edu.ptit.supermarket.model.response.CashierInOrderResponse;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {

  @Query("""
      select new vn.edu.ptit.supermarket.model.response.CashierInOrderResponse(s.id, s.firstName, s.middleName, s.lastName)
      from Staff s where s.id = :staffId and s.role = :role
      """)
  CashierInOrderResponse findCashierById(String staffId, String role);
}
