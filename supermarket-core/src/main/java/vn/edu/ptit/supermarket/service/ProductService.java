package vn.edu.ptit.supermarket.service;

import vn.edu.ptit.supermarket.model.response.ProductPageResponse;

public interface ProductService {
  ProductPageResponse findAllProductForCustomer(int page, int size);
}
