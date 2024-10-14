package vn.edu.ptit.supermarket.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.supermarket.entity.ProductInCart;
import vn.edu.ptit.supermarket.model.response.ProductInCartResponse;

@Repository
public interface ProductInCartRepository extends JpaRepository<ProductInCart, String> {

  @Query("""
    select coalesce(sum(pic.quantity), 0) from ProductInCart pic where pic.cartId = :cartId
    """)
  int countProductInCartByCartId(String cartId);

  @Query("""
    select new vn.edu.ptit.supermarket.model.response.ProductInCartResponse(
      pic.productId, p.name, p.image, p.size, p.priceSell, pd.discountSell, p.quantityInStock, pic.quantity
    )
    from ProductInCart pic
    join Product p on pic.productId = p.id and p.isActivated = true
    left join ProductDiscount pd on pd.productId = p.id and pd.endDate >= current_date()
    where pic.cartId = :cartId
    """)
  List<ProductInCartResponse> findProductInCartsByCartId(String cartId);

  @Query("""
    select new vn.edu.ptit.supermarket.model.response.ProductInCartResponse(
      pic.productId, p.name, p.image, p.size, p.priceSell, pd.discountSell, p.quantityInStock, pic.quantity
    )
    from ProductInCart pic
    join Product p on pic.productId = p.id and p.isActivated = true
    left join ProductDiscount pd on pd.productId = p.id and pd.endDate >= current_date()
    where pic.cartId = :cartId and pic.productId = :productId
    """)
  ProductInCartResponse findProductInCartByProductIdAndCartId(String productId, String cartId);

//  ProductInCart findProductInCartByProductIdAndCartId(String productId, String cartId);

  @Modifying
  @Query("""
    update ProductInCart pic set pic.quantity = :quantity
    where pic.productId = :productId and pic.cartId = :cartId
    """)
  void updateProductInCart(String cartId, String productId, int quantity);

  @Modifying
  @Query("""
    delete from ProductInCart pic
    where pic.productId = :productId and pic.cartId = :cartId
    """)
  void removeProductInCart(String cartId, String productId);
}
