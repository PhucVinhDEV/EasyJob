package com.example.EasyJob.security.controller;


import com.example.EasyJob.common.reponsese.ApiReponsese;
import com.example.EasyJob.security.dto.AuthenticationResponse;
import com.example.EasyJob.security.dto.JWTObject;
import com.example.EasyJob.security.service.AuthenticateService;
import com.example.EasyJob.security.service.JWTService;
import com.example.EasyJob.user.model.reponsese.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.jose.JOSEException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.UUID;

@RequestMapping("/api/auth/v1")
@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthenticateService authenticateService ;
    private final JWTService jwtService ;

    @PostMapping("/login")
    public ApiReponsese<AuthenticationResponse> login(String username, String password) {
        return ApiReponsese.<AuthenticationResponse>builder()
                .result(authenticateService.authenticate(username,password))
                .build();
    }

    // API logout
    @PostMapping("/logout")
    public ApiReponsese<String> logout(@RequestParam UUID userId) {
        authenticateService.Logout(userId);  // Giả sử có dịch vụ để xử lý logout, ví dụ xóa token khỏi Redis.
        return ApiReponsese.<String>builder()
                .result("Logout successful")
                .build();
    }

    @PostMapping("/refresh-token")
    @SecurityRequirement(name = "bearer-key")
    public ApiReponsese<AuthenticationResponse> refreshToken(@RequestHeader("Authorization") String authorizationHeader) throws ParseException, JOSEException, JsonProcessingException {
        // Lấy token từ Authorization header (cắt bỏ tiền tố "Bearer ")
        String refreshToken = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : authorizationHeader;

        AuthenticationResponse newTokens = jwtService.refeshToken(refreshToken);  // Xử lý refresh token và tạo cặp token mới
        return ApiReponsese.<AuthenticationResponse>builder()
                .result(newTokens)
                .build();
    }

    @PostMapping("/verify-token")
    @SecurityRequirement(name = "bearer-key")
    public ApiReponsese<Boolean> verifyToken(@RequestHeader("Authorization") String authorizationHeader) throws ParseException, JOSEException, JsonProcessingException {
        // Lấy token từ Authorization header (cắt bỏ tiền tố "Bearer ")
        String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : authorizationHeader;

        boolean isValid = false;
        if (!jwtService.verifyToken(token, false).isEmpty()) {
            isValid = true;
        }
        return ApiReponsese.<Boolean>builder()
                .result(isValid)
                .build();
    }
}
