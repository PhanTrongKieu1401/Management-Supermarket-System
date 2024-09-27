package vn.edu.ptit.supermarket.core_authentication.constant;

public enum Gender {

  MALE, FEMALE, OTHER;

  public Boolean isValid(String value) {
    for(Gender gender : Gender.values()) {
      if(gender.name().equals(value)) {
        return true;
      }
    }
    return false;
  }
}
