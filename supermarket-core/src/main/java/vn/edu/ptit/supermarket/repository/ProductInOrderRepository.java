package vn.edu.ptit.supermarket.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.ptit.supermarket.entity.ProductInOrder;
import vn.edu.ptit.supermarket.model.response.ProductInOrderResponse;

@Repository
public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, String> {

  @Query("""
      select new vn.edu.ptit.supermarket.model.response.ProductInOrderResponse(
      pid.productId,
      p.name,
      p.image,
      pid.priceSell,
      pid.discountSell,
      pid.priceImport,
      pid.quantity
      ) from ProductInOrder pid
      left join Product p on p.id = pid.productId
      where pid.orderId = :orderId
      """)
  List<ProductInOrderResponse> findAllProductInOrderByOrderId(String orderId);

  @Modifying
  @Transactional
  void deleteByOrderId(String orderId);
}
