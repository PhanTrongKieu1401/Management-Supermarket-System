package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "comment")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment {

  @Id
  private String id;

  private String content;

  @Column(columnDefinition = "MEDIUMTEXT")
  private String image;

  private String parentCommentId;
  private String customerId;
  private String productId;

  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdAt;

  @Column(nullable = false)
  @LastModifiedDate
  private LocalDateTime lastUpdatedAt;
}
