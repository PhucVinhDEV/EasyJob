package com.example.EasyJob.redis.controller;

import com.example.EasyJob.common.reponsese.ApiReponsese;
import com.example.EasyJob.redis.service.RedisService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@AllArgsConstructor
@Hidden
public class RedisController {

    private final RedisService redisService;



    // Lưu giá trị vào Redis
    @PostMapping("/set")
    public String setValue(@RequestParam String key, @RequestParam String value) {
        redisService.setValue(key, value);
        return "Value set successfully!";
    }

    // Lưu giá trị với TTL vào Redis
    @PostMapping("/setWithTTL")
    public String setValueWithTTL(@RequestParam String key,
                                  @RequestParam String value,
                                  @RequestParam long timeout,
                                  @RequestParam TimeUnit unit) {
        redisService.setValueWithTTL(key, value, timeout, unit);
        return "Value with TTL set successfully!";
    }

    // Lấy giá trị từ Redis
    @GetMapping("/get")
    public ApiReponsese<Object> getValue(@RequestParam String key) {
        return ApiReponsese.builder()
                .result(redisService.getValue(key))
                .build();
    }

    // Xóa giá trị từ Redis
    @DeleteMapping("/delete")
    public String deleteValue(@RequestParam String key) {
        redisService.deleteValue(key);
        return "Value deleted successfully!";
    }

    // Kiểm tra sự tồn tại của một key
    @GetMapping("/hasKey")
    public boolean hasKey(@RequestParam String key) {
        return redisService.hasKey(key);
    }
}
