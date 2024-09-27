package vn.edu.ptit.supermarket.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.supermarket.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
}
