package com.example.EasyJob.security.config;

import com.example.EasyJob.common.util.MessageUtil;
import com.example.EasyJob.security.service.JWTService;
import com.example.EasyJob.security.util.EndponitUtil;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.ParseException;

@AllArgsConstructor
@Component
public class RedisJwtAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if(EndponitUtil.isRedisSecuredEndpoint(requestURI)) {
            String token = extractTokenFromRequest(request);
            String userId = null;
            try {
                userId = jwtService.verifyToken(token,false);
                // Thiết lập thông tin xác thực vào SecurityContext
                Authentication authentication = new UsernamePasswordAuthenticationToken(userId, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JOSEException | ParseException e) {
                throw new RuntimeException(e);
            }
        }
        // Tiếp tục chuỗi filter nếu không có lỗi
        filterChain.doFilter(request, response);
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        // Lấy giá trị header Authorization từ request
        String bearerToken = request.getHeader("Authorization");

        // Kiểm tra xem bearerToken có khác null và có bắt đầu bằng "Bearer " không
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            // Trả về phần token không bao gồm tiền tố "Bearer "
            return bearerToken.substring(7);
        }else {
            throw new RuntimeException(MessageUtil.TOKEN_EXPIRED);
        }

        // Nếu không có token, trả về null

    }
}
