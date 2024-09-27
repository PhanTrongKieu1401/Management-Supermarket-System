package vn.edu.ptit.supermarket.constant;

public enum OrderStatus {

  PAYING, PENDING, APPROVED, DELIVERING, DELIVERED, COMPLETED, CANCELLED;

  public Boolean isValid(String value) {
    for(OrderStatus status : OrderStatus.values()) {
      if(status.name().equals(value)) {
        return true;
      }
    }
    return false;
  }
}
