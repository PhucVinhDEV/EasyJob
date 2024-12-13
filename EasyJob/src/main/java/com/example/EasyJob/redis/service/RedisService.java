package com.example.EasyJob.redis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class RedisService {
    private final ObjectMapper objectMapper;

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate,ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    // Lưu giá trị với một key vào Redis
    public void setValue(String key, Object value) {
        try {
            // Serialize JWTObject thành JSON
            String jsonValue = objectMapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, jsonValue);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing JWTObject to JSON", e);
        }
    }

    // Lưu giá trị với thời gian sống (TTL)
    public void setValueWithTTL(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    // Lấy giá trị từ Redis theo key
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // Xóa giá trị từ Redis theo key
    public void deleteValue(String key) {
        redisTemplate.delete(key);
    }

    // Kiểm tra xem một key có tồn tại trong Redis không
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}
