package vn.edu.ptit.supermarket.mapper;

import java.math.BigDecimal;
import vn.edu.ptit.supermarket.entity.ProductInOrder;
import vn.edu.ptit.supermarket.model.request.ProductOrderRequest;

public interface ProductInOrderMapper {

  ProductInOrder mapToProductInOrder(ProductOrderRequest productOrderRequest, String orderId, BigDecimal priceImport);
}
