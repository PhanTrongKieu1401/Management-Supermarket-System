package vn.edu.ptit.supermarket.core_exception.exception;

public class UnauthorizedException extends BaseException {

  public UnauthorizedException() {
    setStatus(401);
    setCode("UnauthorizedException");
  }
}
