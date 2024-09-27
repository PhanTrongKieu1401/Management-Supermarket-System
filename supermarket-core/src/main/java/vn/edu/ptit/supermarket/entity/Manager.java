package vn.edu.ptit.supermarket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import vn.edu.ptit.supermarket.core_authentication.entity.Member;

@Entity
@Table(name = "manager")
public class Manager extends Member {

}
