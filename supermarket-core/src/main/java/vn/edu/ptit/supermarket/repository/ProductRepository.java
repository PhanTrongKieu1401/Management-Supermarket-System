package vn.edu.ptit.supermarket.repository;

import io.lettuce.core.dynamic.annotation.Param;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.supermarket.entity.Product;
import vn.edu.ptit.supermarket.model.response.ProductResponse;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

  int countProductByIsActivated(Boolean isActivated);

  @Query("""
      select new vn.edu.ptit.supermarket.model.response.ProductResponse(
      p.id,
      p.name,
      p.image,
      p.size,
      p.priceSell,
      pd.discountSell,
      p.quantityInStock,
      p.description,
      p.productionBatch,
      round(coalesce(avg(e.point), 0),1),
      c.name
      ) from Product p
      join Category c on p.categoryId = c.id
      left join ProductDiscount pd on pd.productId = p.id and pd.endDate >= current_date()
      left join Evaluation e on p.id = e.productId
      group by
      p.id, p.name, p.image, p.size, p.priceSell, p.quantityInStock, p.description,
      p.productionBatch, c.name, pd.discountSell
      order by p.id
      limit :limit offset :offset
      """)
  List<ProductResponse> findAllProductForCustomer(@Param("limit") int limit, @Param("offset") int offset);
}
