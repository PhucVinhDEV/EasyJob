package com.example.EasyJob.security.service;

import com.example.EasyJob.common.exception.AppException;
import com.example.EasyJob.common.util.MessageUtil;
import com.example.EasyJob.redis.service.RedisService;
import com.example.EasyJob.role.model.Role;
import com.example.EasyJob.security.dto.AuthenticationResponse;
import com.example.EasyJob.security.dto.InstropecReponsee;
import com.example.EasyJob.security.dto.JWTObject;
import com.example.EasyJob.user.model.User;
import com.example.EasyJob.user.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public interface JWTService {
    JWTObject generateToken(User user);
    AuthenticationResponse refeshToken(String token) throws JOSEException, ParseException, JsonProcessingException;
    String verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException, JsonProcessingException;

}

@Slf4j
@Service
class JWTServiceImpl implements JWTService {

    private final UserRepository userRepository;

    @Value("${spring.security.jwt.secretKey}")
    private String SignerKey;

    @Value("${spring.security.AccessExperienceTime}")
    private Integer AccessExperienceTime;

    @Value("${spring.security.RefreshExperienceTime}")
    private Integer RefreshExperienceTime;

    private final RedisService redisService;
    private final ObjectMapper objectMapper;
    public JWTServiceImpl(RedisService redisService, UserRepository userRepository, ObjectMapper objectMapper) {
        this.redisService = redisService;
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public JWTObject generateToken(User user) {
        // Access token
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet accessClaims = new JWTClaimsSet.Builder()
                .subject(user.getId().toString())
                .issuer("BitzNomad")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(AccessExperienceTime, ChronoUnit.MINUTES).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();

        Payload accessPayload = new Payload(accessClaims.toJSONObject());
        JWSObject accessToken = new JWSObject(header, accessPayload);

        // Refresh token
        JWTClaimsSet refreshClaims = new JWTClaimsSet.Builder()
                .subject(user.getId().toString())
                .issuer("BitzNomad")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(RefreshExperienceTime, ChronoUnit.HOURS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", "refresh_token")
                .build();

        Payload refreshPayload = new Payload(refreshClaims.toJSONObject());
        JWSObject refreshToken = new JWSObject(header, refreshPayload);
        try {
            accessToken.sign(new MACSigner(SignerKey.getBytes()));
            refreshToken.sign(new MACSigner(SignerKey.getBytes()));
            JWTObject jwtObject = JWTObject.builder()
                    .accesstoken(accessToken.serialize())
                    .refreshtoken(refreshToken.serialize())
                    .build();
            String jwtObjectJson = objectMapper.writeValueAsString(jwtObject);
            redisService.setValueWithTTL(user.getId().toString(), jwtObjectJson,RefreshExperienceTime, TimeUnit.HOURS);
            return jwtObject;
        } catch (JOSEException | JsonProcessingException e) {
            log.error("JWT Exception", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public AuthenticationResponse refeshToken(String token) throws JOSEException, ParseException, JsonProcessingException {
     String uuid =   verifyToken(token,true);
        User user = userRepository.findById(UUID.fromString(uuid)).orElseThrow(
                () -> new RuntimeException(MessageUtil.UUID_NOT_FOUND)
        );
        JWTObject jwtObject = generateToken(user);
        return AuthenticationResponse.builder()
                .authenticated(true)
                .token(jwtObject)
                .build();
    }



    @Override
    public String verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException, JsonProcessingException {
        // Giải mã token và lấy các claims
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

        // Kiểm tra chữ ký của token ngay lập tức
        JWSVerifier verifier = new MACVerifier(SignerKey.getBytes());
        boolean verified = signedJWT.verify(verifier);
        if (!verified) {
            log.error("Token signature verification failed cho user ID: {}", claimsSet.getSubject());
            throw new AppException(MessageUtil.JWT_AUTHENTICATION_FAILED);
        }

        // Kiểm tra thời gian hết hạn của token
        Date expirationTime = claimsSet.getExpirationTime();
        if (expirationTime != null && expirationTime.before(new Date())) {
            log.error("Token đã hết hạn cho user ID: {}", claimsSet.getSubject());
            throw new AppException(MessageUtil.TOKEN_EXPIRED);
        }
        // Lấy JWTObject từ Redis dựa trên userId
        Object jwtObjectFromRedis = redisService.getValue(claimsSet.getSubject());

        if (jwtObjectFromRedis == null) {
            log.error("JWT Object không tồn tại trong Redis cho user ID: {}", claimsSet.getSubject());
            throw new AppException(MessageUtil.JWT_AUTHENTICATION_FAILED);
        }
// Ép kiểu Object thành JWTObject
        JWTObject jwtObject = objectMapper.readValue(jwtObjectFromRedis.toString(), JWTObject.class);
// Kiểm tra xem đối tượng có phải là JWTObject không
//        if (!(jwtObjectFromRedis instanceof JWTObject)) {
//            log.error("Đối tượng lấy từ Redis không phải là JWTObject cho user ID: {}", claimsSet.getSubject() + ":" + jwtObjectFromRedis);
//            throw new AppException(MessageUtil.JWT_AUTHENTICATION_FAILED);
//        }




        String scope = claimsSet.getStringClaim("scope");
        if (isRefresh || "refresh_token".equals(scope)) {
            // Kiểm tra xem token là Refresh Token hợp lệ không
            if (!jwtObject.getRefreshtoken().equals(token)) {
                log.error("Refresh token không hợp lệ cho user ID: {}",  claimsSet.getSubject());
                throw new AppException(MessageUtil.REFRESH_TOKEN_INVALID);
            }
        } else {
            // Kiểm tra xem token là Access Token hợp lệ không
            if (!jwtObject.getAccesstoken().equals(token)) {
                log.error("Access token không hợp lệ cho user ID: {}",  claimsSet.getSubject());
                throw new AppException(MessageUtil.ACCESS_TOKEN_INVALID);
            }
        }
        // Trả về SignedJWT nếu tất cả các bước xác thực đều hợp lệ
        return claimsSet.getSubject();


    }


    private String buildScope(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (user.getRole() != null ) {
            Role role = user.getRole();
            stringJoiner.add("ROLE_" + role.getRoleName());
            if (role.getPermissions() != null && !role.getPermissions().isEmpty()) {
                role.getPermissions()
                        .forEach(permission -> stringJoiner.add(permission.getName()));
            }
        }
        return stringJoiner.toString();
    }
}