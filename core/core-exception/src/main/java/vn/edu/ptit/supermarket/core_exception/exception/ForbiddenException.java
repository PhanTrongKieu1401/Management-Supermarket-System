package vn.edu.ptit.supermarket.core_exception.exception;

public class ForbiddenException extends BaseException {

  public ForbiddenException() {
    setStatus(403);
    setCode("ForbiddenException");
  }
}
