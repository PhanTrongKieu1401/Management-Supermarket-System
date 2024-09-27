package vn.edu.ptit.supermarket.core_exception.exception;

public class ConflictException extends BaseException{

  public ConflictException() {
    setStatus(409);
    setCode("ConflictException");
  }
}
