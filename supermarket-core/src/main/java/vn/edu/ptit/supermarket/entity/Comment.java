package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.ptit.supermarket.core_authentication.entity.BaseEntity;

@Data
@Entity
@Table(name = "comment")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Comment extends BaseEntity {

  private String content;
  private String parentCommentId;
  private String customerId;
  private String productId;
}
