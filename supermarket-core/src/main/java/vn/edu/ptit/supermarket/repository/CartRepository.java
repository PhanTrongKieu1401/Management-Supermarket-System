package vn.edu.ptit.supermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.supermarket.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {

}
