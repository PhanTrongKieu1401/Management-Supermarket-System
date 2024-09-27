package vn.edu.ptit.supermarket.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vn.edu.ptit.supermarket.core_redis.service.RedisService;
import vn.edu.ptit.supermarket.repository.CategoryRepository;
import vn.edu.ptit.supermarket.repository.ProductRepository;
import vn.edu.ptit.supermarket.service.CategoryService;
import vn.edu.ptit.supermarket.service.ProductService;
import vn.edu.ptit.supermarket.service.TestService;
import vn.edu.ptit.supermarket.service.impl.CategoryServiceImpl;
import vn.edu.ptit.supermarket.service.impl.ProductServiceImpl;
import vn.edu.ptit.supermarket.service.impl.TestServiceImpl;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"vn.edu.ptit.supermarket.repository"})
@ComponentScan(basePackages = {"vn.edu.ptit.supermarket.repository"})
@EntityScan(basePackages = {"vn.edu.ptit.supermarket.entity"})
public class SupermarketCoreConfiguration {

  @Bean
  public TestService testService(RedisService redisService) {
    return new TestServiceImpl(redisService);
  }

  @Bean
  public ProductService productService(ProductRepository productRepository) {
    return new ProductServiceImpl(productRepository);
  }

  @Bean
  public CategoryService categoryService(CategoryRepository categoryRepository) {
    return new CategoryServiceImpl(categoryRepository);
  }
}
