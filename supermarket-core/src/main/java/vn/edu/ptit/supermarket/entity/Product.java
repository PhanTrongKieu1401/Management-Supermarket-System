package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "product")
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Product {

  @Id
  @Column(unique = true)
  private String id;

  private String name;

  @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
  private String image;

  private String size;

  @Column(precision = 10, scale = 1, nullable = false)
  private BigDecimal priceSell;

  @Column(precision = 10, scale = 1, nullable = false)
  private BigDecimal priceImport;

  private int quantityInStock;
  private String description;
  private Boolean isActivated = true;
  private String categoryId;

  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdAt;

  @Column(nullable = false)
  @LastModifiedDate
  private LocalDateTime lastUpdatedAt;
}
