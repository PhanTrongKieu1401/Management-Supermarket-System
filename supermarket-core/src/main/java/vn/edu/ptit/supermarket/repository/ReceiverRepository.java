package vn.edu.ptit.supermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.ptit.supermarket.entity.Receiver;
import vn.edu.ptit.supermarket.model.response.ReceiverResponse;

@Repository
public interface ReceiverRepository extends JpaRepository<Receiver, String> {

  @Query("""
      select new vn.edu.ptit.supermarket.model.response.ReceiverResponse(r.fullName, r.phone, r.addressDetail, r.ward, r.district, r.province)
      from Receiver r where r.orderId = :orderId
      """)
  ReceiverResponse getReceiverByOrderId(String orderId);

  @Modifying
  @Transactional
  void deleteByOrderId(String orderId);
}
