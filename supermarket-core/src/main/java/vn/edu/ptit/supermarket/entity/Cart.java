package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cart")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
}
