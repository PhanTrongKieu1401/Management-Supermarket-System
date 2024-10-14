package vn.edu.ptit.supermarket.exception;

import vn.edu.ptit.supermarket.core_exception.exception.BadRequestException;

public class ProductNotEnoughException extends BadRequestException {

  public ProductNotEnoughException(String productName) {
    setStatus(400);
    setCode("ProductNotEnoughException");
    addParams("productName", productName);
  }
}
