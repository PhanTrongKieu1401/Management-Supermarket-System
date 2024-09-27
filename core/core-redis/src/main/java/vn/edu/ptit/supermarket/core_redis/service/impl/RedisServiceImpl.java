package vn.edu.ptit.supermarket.core_redis.service.impl;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vn.edu.ptit.supermarket.core_redis.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

  private final RedisTemplate<String, Object> redisTemplate;

  public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Override
  public void save(String key, Object value, long timeToLive, TimeUnit timeUnit) {
    redisTemplate.opsForValue().set(key, value, timeToLive, timeUnit);
  }

  @Override
  public void save(String key, String hashKey, Object value) {
    redisTemplate.opsForHash().put(key, hashKey, value);
  }

  @Override
  public Optional<Object> get(String key, String hashKey) {
    return Optional.ofNullable(redisTemplate.opsForHash().get(key, hashKey));
  }

  @Override
  public Optional<Object> get(String key) {
    return Optional.ofNullable(redisTemplate.opsForValue().get(key));
  }

  @Override
  public void delete(String key, String hashKey) {
    redisTemplate.opsForHash().delete(key, hashKey);
  }

  @Override
  public void delete(String key) {
    redisTemplate.delete(key);
  }

  @Override
  public <T> T getOrDefault(String key, T defaultValue) {
    Object value = redisTemplate.opsForValue().get(key);
    return (value != null) ? (T) value : defaultValue;
  }

  @Override
  public <T> T getOrDefault(String key, String hashKey, T defaultValue) {
    Object value = redisTemplate.opsForHash().get(key, hashKey);
    return (value != null) ? (T) value : defaultValue;
  }
}
