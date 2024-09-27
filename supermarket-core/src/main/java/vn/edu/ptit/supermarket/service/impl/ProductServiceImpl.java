package vn.edu.ptit.supermarket.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.edu.ptit.supermarket.model.response.ProductPageResponse;
import vn.edu.ptit.supermarket.model.response.ProductResponse;
import vn.edu.ptit.supermarket.repository.ProductRepository;
import vn.edu.ptit.supermarket.service.ProductService;

@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  private final static int LIMIT = 20;

  @Override
  public ProductPageResponse findAllProductForCustomer(int page, int size) {
    log.info("findAllProductForHomeCustomer: page {}, size {}", page, size);
    int offset = (page - 1) * size;
    List<ProductResponse> productResponses = productRepository.findAllProductForCustomer(size, offset);
    int totalPages = (int) Math.ceil((double) productRepository.countProductByIsActivated(true) / size);
    return new ProductPageResponse(productResponses, totalPages);
  }
}
