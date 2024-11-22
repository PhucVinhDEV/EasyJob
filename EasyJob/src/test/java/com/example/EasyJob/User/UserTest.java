package com.example.EasyJob.User;


import com.example.EasyJob.user.model.User;
import com.example.EasyJob.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        // Thiết lập dữ liệu test ban đầu
        userRepository.save(User.builder()
                .email("test@test.com")  // Đảm bảo tên thuộc tính đúng
                .password("123123")
                .build());
    }

    @Test
    public void testSaveUser() {
        // Test lưu user mới
        userRepository.save(User.builder()
                .email("test@test123.com")
                .password("123123")
                .build());

        Optional<User> savedUser = userRepository.findByEmail("test@test123.com");
        Assertions.assertTrue(savedUser.isPresent(), "New user should be saved");
        Assertions.assertEquals("123123", savedUser.get().getPassword());
    }

}
