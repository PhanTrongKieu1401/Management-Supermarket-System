package vn.edu.ptit.supermarket.service;

import java.math.BigDecimal;
import vn.edu.ptit.supermarket.entity.Product;
import vn.edu.ptit.supermarket.model.response.ProductPageResponse;

public interface ProductService {
  BigDecimal findPriceImportByProductId(String productId);
  ProductPageResponse findAllProductForCustomer(String keySearch, String type, String sortType, String keySort, int page, int size);
  Product findProductById(String productId);
  void saveProduct(Product product);
}
