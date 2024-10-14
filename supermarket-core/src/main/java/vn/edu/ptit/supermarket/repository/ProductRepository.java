package vn.edu.ptit.supermarket.repository;

import io.lettuce.core.dynamic.annotation.Param;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.supermarket.entity.Product;
import vn.edu.ptit.supermarket.model.response.ProductResponse;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

  @Query(value = """
      select count(p.id) from Product p
      where (:keySearch is null or p.name like %:keySearch%)
        and (:type is null or :type = 'ALL' or p.categoryId = :type)
      """)
  int countProductByIsActivated(
      @Param("keySearch") String keySearch,
      @Param("type") String type
  );

  @Query("""
      SELECT p.priceImport FROM Product p WHERE p.id = :id
      """)
  BigDecimal findPriceImportById(@Param("id") String id);

  Product findProductById(String id);

  @Query(value = """
      select new vn.edu.ptit.supermarket.model.response.ProductResponse(
      p.id,
      p.name,
      p.image,
      p.size,
      p.priceSell,
      pd.discountSell,
      p.quantityInStock,
      p.description,
      round(coalesce(avg(e.point), 0),1),
      c.name
      ) from Product p
      join Category c on p.categoryId = c.id
      left join ProductDiscount pd on pd.productId = p.id and pd.endDate >= current_date()
      left join Evaluation e on p.id = e.productId
      where (:keySearch is null or p.name like %:keySearch%)
        and (:type is null or :type = 'ALL' or p.categoryId = :type)
      group by
      p.id, p.name, p.image, p.size, p.priceSell, p.quantityInStock, p.description, c.name, pd.discountSell
      order by
        case
          when :keySort = 'name' and :sortType = 'ASC' then p.name
          when :keySort = 'price' and :sortType = 'ASC' then p.priceSell
          when :keySort = 'rating' and :sortType = 'ASC' then round(coalesce(avg(e.point), 0),1)
        end asc,
        case
          when :keySort = 'price' and :sortType = 'DESC' then p.priceSell
          when :keySort = 'rating' and :sortType = 'DESC' then round(coalesce(avg(e.point), 0),1)
        end desc,
        p.name asc 
      limit :limit offset :offset
      """)
  List<ProductResponse> findAllProductForCustomer(
      @Param("keySearch") String keySearch,
      @Param("type") String type,
      @Param("keySort") String keySort,
      @Param("sortType") String sortType,
      @Param("limit") int limit,
      @Param("offset") int offset);
}
