package vn.edu.ptit.supermarket.core_redis.service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public interface RedisService {

  void save(String key, Object value, long timeToLive, TimeUnit timeUnit);

  void save(String key, String hashKey, Object value);

  Optional<Object> get(String key, String hashKey);

  Optional<Object> get(String key);

  void delete(String key, String hashKey);

  void delete(String key);

  <T> T getOrDefault(String key, T defaultValue);

  <T> T getOrDefault(String key, String hashKey, T defaultValue);
}
