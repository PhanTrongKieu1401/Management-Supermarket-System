package vn.edu.ptit.supermarket.core_authentication.constant;

public enum Role {

  MANAGER, WAREHOUSE_MANAGER, CASHIER, SHIPPER, CUSTOMER;

  public Boolean isValid(String value) {
    for(Role role : Role.values()) {
      if(role.name().equals(value)) {
        return true;
      }
    }
    return false;
  }
}
