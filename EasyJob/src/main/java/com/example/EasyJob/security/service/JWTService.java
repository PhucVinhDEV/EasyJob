package com.example.EasyJob.security.service;

import com.example.EasyJob.security.dto.AuthenticationResponse;
import com.example.EasyJob.security.dto.InstropecReponsee;
import com.example.EasyJob.user.model.User;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;

public interface JWTService {
    String generateToken(User user);
    AuthenticationResponse refeshToken(String token) throws JOSEException, ParseException;
    InstropecReponsee instropecReponsee(String token) throws ParseException, JOSEException;
    SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException;
}
