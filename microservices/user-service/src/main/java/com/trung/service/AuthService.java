package com.trung.service;

import com.trung.payload.dto.SignupDTO;
import com.trung.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse login(String username, String password) throws Exception;

    AuthResponse signup(SignupDTO request) throws Exception;

    AuthResponse getAccessTokenFromRefreshToken(String refreshToken) throws Exception;
}
