package vn.edu.ptit.supermarket.core_redis.constant;

public class CacheConstant {

//  public CacheConstant() {}

  public static final String TEST = "TEST";
  public static final String LOCK_LOGIN_TIME_KEY = "LOCK_LOGIN_TIME_KEY";
  public static final String UNLOCK_LOGIN_TIME_KEY = "UNLOCK_LOGIN_TIME_KEY";

  public static final String LOGIN_FAILED_ATTEMPT_KEY = "LOGIN_FAILED_ATTEMPT_KEY";
  public static final String REGISTER_KEY = "REGISTER_KEY";

  public static final String OTP_VERIFICATION_ACCOUNT_KEY = "OTP_VERIFICATION_ACCOUNT_KEY";
  public static final Long OTP_TTL_MINUTES = 3L;

  public static final String RESET_PASSWORD_KEY = "RESET_PASSWORD_KEY";
  public static final String RESET_PASSWORD_OTP_KEY = "RESET_PASSWORD_OTP_KEY";

  public static final String ORDER_KEY = "ORDER_KEY";
}
