package vn.edu.ptit.supermarket.service.impl;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vn.edu.ptit.supermarket.core_redis.constant.CacheConstant;
import vn.edu.ptit.supermarket.core_redis.service.RedisService;
import vn.edu.ptit.supermarket.service.TestService;

@Service
@Component
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

  private static final Logger log = LoggerFactory.getLogger(TestServiceImpl.class);
  private final RedisService redisService;

  @Override
  public String testRedis(String name) {
    log.info("(testRedis) name: {}", name);
    redisService.save(CacheConstant.TEST, name, 10, TimeUnit.SECONDS);

    return redisService.get(CacheConstant.TEST).get().toString();
  }
}
