package com.example.EasyJob.security.service;

import org.springframework.stereotype.Service;

@Service
public interface authenticateService {
    authenticateService authenticate(String username, String password);
    void Logout(String token);
}
