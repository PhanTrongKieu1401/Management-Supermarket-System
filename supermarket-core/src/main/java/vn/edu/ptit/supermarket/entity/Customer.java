package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import vn.edu.ptit.supermarket.core_authentication.entity.Member;

@Data
@Entity
@Table(name = "customer")
public class Customer extends Member {

}
