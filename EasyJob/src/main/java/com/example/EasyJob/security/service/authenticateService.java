package com.example.EasyJob.security.service;

import org.springframework.stereotype.Service;

@Service
public interface authenticateService {
    public boolean authenticate(String username, String password);
}
