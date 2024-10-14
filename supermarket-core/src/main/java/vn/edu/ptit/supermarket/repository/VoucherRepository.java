package vn.edu.ptit.supermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.supermarket.entity.Voucher;
import vn.edu.ptit.supermarket.model.response.VoucherInOrderResponse;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, String> {

  @Query("""
      select new vn.edu.ptit.supermarket.model.response.VoucherInOrderResponse(v.id, v.voucherCode, v.value)
      from Voucher v
      """)
  VoucherInOrderResponse findVoucherById(String voucherId);
}
