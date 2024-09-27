package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.ptit.supermarket.core_authentication.entity.BaseEntity;

@Data
@Entity
@Table(name = "product")
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Product extends BaseEntity {

  private String name;
  private String image;
  private String size;
  private BigDecimal priceSell;
  private BigDecimal priceImport;
  private int quantityInStock;
  private String description;
  private String productionBatch;
  private Date productionDate;
  private Date expireDate;
  private Boolean isActivated = true;
  private String categoryId;
}
