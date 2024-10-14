package vn.edu.ptit.supermarket.repository;

import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.ptit.supermarket.entity.Order;
import vn.edu.ptit.supermarket.model.response.OrderResponse;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

  @Query("""
  select new vn.edu.ptit.supermarket.model.response.OrderResponse(
    o.id,
    o.paymentMethod,
    o.paymentStatus,
    o.paymentDate,
    o.orderStatus,
    o.transportFee,
    coalesce(sum(
      case
        when pio.discountSell = 0 then cast(pio.priceSell as long) * cast(pio.quantity as long)
        else cast(pio.discountSell as long) * cast(pio.quantity as long)
      end
    ), 0)
  ) from Order o
  join ProductInOrder pio on o.id = pio.orderId
  where o.customerId = :customerId
  group by o.id,
    o.paymentMethod,
    o.paymentStatus,
    o.paymentDate,
    o.orderStatus,
    o.transportFee
  order by o.paymentDate desc
  """)
  List<OrderResponse> getOrdersByCustomerId(String customerId);

  @Modifying
  @Transactional
  @Query("""
      update Order o set o.paymentStatus = :paymentStatus where o.id = :id
      """)
  void updatePaymentStatus(@Param("id") String id, @Param("paymentStatus") String paymentStatus);

  @Modifying
  @Transactional
  @Query("""
      update Order o set o.orderStatus = :orderStatus where o.id = :id
      """)
  void updateOrderStatus(@Param("id") String id, @Param("orderStatus") String orderStatus);
}
