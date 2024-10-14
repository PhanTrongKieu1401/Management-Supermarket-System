package vn.edu.ptit.supermarket.mapper.impl;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import vn.edu.ptit.supermarket.entity.ProductInOrder;
import vn.edu.ptit.supermarket.mapper.ProductInOrderMapper;
import vn.edu.ptit.supermarket.model.request.ProductOrderRequest;

@Component
public class ProductInOrderMapperImpl implements ProductInOrderMapper {

  @Override
  public ProductInOrder mapToProductInOrder(ProductOrderRequest productOrderRequest, String orderId,
      BigDecimal priceImport) {
    ProductInOrder productInOrder = new ProductInOrder();
    productInOrder.setId("PIO" + System.currentTimeMillis());
    productInOrder.setQuantity(productOrderRequest.getQuantity());
    productInOrder.setPriceSell(productOrderRequest.getPriceSell());
    productInOrder.setDiscountSell(productOrderRequest.getDiscountSell());
    productInOrder.setPriceImport(priceImport);
    productInOrder.setOrderId(orderId);
    productInOrder.setProductId(productOrderRequest.getProductId());
    return productInOrder;
  }
}
