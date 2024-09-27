package vn.edu.ptit.supermarket.core_authentication.exception;

import vn.edu.ptit.supermarket.core_exception.exception.BadRequestException;

public class TypeResendInvalidException extends BadRequestException {

  public TypeResendInvalidException(String type) {
    setStatus(400);
    setCode("TypeResendInvalidException");
    addParams("type", type);
  }
}
