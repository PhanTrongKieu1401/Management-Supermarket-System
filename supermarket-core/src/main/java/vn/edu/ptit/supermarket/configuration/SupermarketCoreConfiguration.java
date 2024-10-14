package vn.edu.ptit.supermarket.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vn.edu.ptit.supermarket.core_email.healper.EmailHelper;
import vn.edu.ptit.supermarket.core_redis.service.RedisService;
import vn.edu.ptit.supermarket.facade.CartFacadeService;
import vn.edu.ptit.supermarket.facade.OrderFacadeService;
import vn.edu.ptit.supermarket.facade.impl.CartFacadeServiceImpl;
import vn.edu.ptit.supermarket.facade.impl.OrderFacadeServiceImpl;
import vn.edu.ptit.supermarket.mapper.OrderMapper;
import vn.edu.ptit.supermarket.mapper.ProductInOrderMapper;
import vn.edu.ptit.supermarket.repository.CategoryRepository;
import vn.edu.ptit.supermarket.repository.OrderRepository;
import vn.edu.ptit.supermarket.repository.ProductInCartRepository;
import vn.edu.ptit.supermarket.repository.ProductInOrderRepository;
import vn.edu.ptit.supermarket.repository.ProductRepository;
import vn.edu.ptit.supermarket.repository.ReceiverRepository;
import vn.edu.ptit.supermarket.service.StaffService;
import vn.edu.ptit.supermarket.service.CategoryService;
import vn.edu.ptit.supermarket.service.CustomerService;
import vn.edu.ptit.supermarket.service.OrderService;
import vn.edu.ptit.supermarket.service.ProductInCartService;
import vn.edu.ptit.supermarket.service.ProductInOrderService;
import vn.edu.ptit.supermarket.service.ProductService;
import vn.edu.ptit.supermarket.service.ReceiverService;
import vn.edu.ptit.supermarket.service.VoucherService;
import vn.edu.ptit.supermarket.service.impl.CategoryServiceImpl;
import vn.edu.ptit.supermarket.service.impl.OrderServiceImpl;
import vn.edu.ptit.supermarket.service.impl.ProductInCartServiceImpl;
import vn.edu.ptit.supermarket.service.impl.ProductInOrderServiceImpl;
import vn.edu.ptit.supermarket.service.impl.ProductServiceImpl;
import vn.edu.ptit.supermarket.service.impl.ReceiverServiceImpl;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"vn.edu.ptit.supermarket.repository"})
@ComponentScan(basePackages = {"vn.edu.ptit.supermarket.repository"})
@EntityScan(basePackages = {"vn.edu.ptit.supermarket.entity"})
public class SupermarketCoreConfiguration {

  @Bean
  public ProductService productService(ProductRepository productRepository) {
    return new ProductServiceImpl(productRepository);
  }

  @Bean
  public CategoryService categoryService(CategoryRepository categoryRepository) {
    return new CategoryServiceImpl(categoryRepository);
  }

  @Bean
  public CartFacadeService cartFacadeService(ProductService productService,ProductInCartService productInCartService) {
    return new CartFacadeServiceImpl(productService, productInCartService);
  }

  @Bean
  public ProductInCartService productInCartService(
      ProductInCartRepository productInCartRepository) {
    return new ProductInCartServiceImpl(productInCartRepository);
  }

  @Bean
  public OrderFacadeService orderFacadeService(OrderService orderService,
      ProductService productService, ProductInOrderService productInOrderService,
      ReceiverService receiverService, VoucherService voucherService, StaffService staffService,
      CustomerService customerService, RedisService redisService, EmailHelper emailHelper,
      OrderMapper orderMapper, ProductInOrderMapper productInOrderMapper) {
    return new OrderFacadeServiceImpl(orderService, productService, productInOrderService,
        receiverService, voucherService, staffService, customerService, redisService, emailHelper, orderMapper, productInOrderMapper);
  }

  @Bean
  public OrderService orderService(OrderRepository orderRepository) {
    return new OrderServiceImpl(orderRepository);
  }

  @Bean
  public ProductInOrderService productInOrderService(
      ProductInOrderRepository productInOrderRepository) {
    return new ProductInOrderServiceImpl(productInOrderRepository);
  }

  @Bean
  public ReceiverService receiverService(ReceiverRepository receiverRepository) {
    return new ReceiverServiceImpl(receiverRepository);
  }
}
