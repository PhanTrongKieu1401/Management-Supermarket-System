package vn.edu.ptit.supermarket.core_exception.exception;

public class InternalErrorException extends BaseException {

  public InternalErrorException() {
    setStatus(500);
    setCode("InternalErrorException");
  }
}
