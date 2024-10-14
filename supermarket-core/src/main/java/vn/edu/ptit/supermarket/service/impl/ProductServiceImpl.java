package vn.edu.ptit.supermarket.service.impl;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.edu.ptit.supermarket.entity.Product;
import vn.edu.ptit.supermarket.model.response.ProductPageResponse;
import vn.edu.ptit.supermarket.model.response.ProductResponse;
import vn.edu.ptit.supermarket.repository.ProductRepository;
import vn.edu.ptit.supermarket.service.ProductService;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  private final static int LIMIT = 20;

  @Override
  public BigDecimal findPriceImportByProductId(String productId) {
    log.info("(findPriceImportByProductId)productId: {}", productId);
    return productRepository.findPriceImportById(productId);
  }

  @Override
  public ProductPageResponse findAllProductForCustomer(String keySearch, String type, String sortType, String keySort, int page, int size) {
    log.info("(findAllProductForHomeCustomer)keySearch: {}, type: {}, sortType: {}, keySort: {}, page: {}, size: {}", keySearch, type, sortType, keySort, page, size);
    int offset = (page - 1) * size;
    List<ProductResponse> productResponses = productRepository.findAllProductForCustomer(keySearch, type, keySort, sortType, size, offset);
    int totalPages = (int) Math.ceil((double) productRepository.countProductByIsActivated(keySearch, type) / size);
    return new ProductPageResponse(productResponses, totalPages);
  }

  @Override
  public Product findProductById(String productId) {
    log.info("(findProductById)productId: {}", productId);
    return productRepository.findProductById(productId);
  }

  @Override
  public void saveProduct(Product product) {
    log.info("(saveProduct)product: {}", product);
    productRepository.save(product);
  }
}
