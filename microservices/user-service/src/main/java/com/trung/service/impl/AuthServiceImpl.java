package com.trung.service.impl;

import com.trung.model.User;
import com.trung.payload.dto.SignupDTO;
import com.trung.payload.response.AuthResponse;
import com.trung.payload.response.TokenResponse;
import com.trung.repository.UserRepository;
import com.trung.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final KeycloakService keycloakService;

    @Override
    public AuthResponse login(String username, String password) throws Exception {
        TokenResponse tokenResponse = keycloakService.getAdminAccessToken(
                username,
                password,
                "password",
                null
        );

        AuthResponse authResponse = new AuthResponse();
        authResponse.setRefreshToken(tokenResponse.getRefreshToken());
        authResponse.setJwt(tokenResponse.getAccessToken());
        authResponse.setMessage("Login successful");
        return authResponse;
    }

    @Override
    public AuthResponse signup(SignupDTO request) throws Exception {
        keycloakService.createUser(request);

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setFullName(request.getFullName());
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        TokenResponse tokenResponse = keycloakService.getAdminAccessToken(
                request.getUsername(),
                request.getPassword(),
                "password",
                null
        );

        AuthResponse authResponse = new AuthResponse();
        authResponse.setRefreshToken(tokenResponse.getRefreshToken());
        authResponse.setJwt(tokenResponse.getAccessToken());
        authResponse.setMessage("User registered successfully");
        authResponse.setRole(request.getRole());
        return authResponse;
    }

    @Override
    public AuthResponse getAccessTokenFromRefreshToken(String refreshToken) throws Exception {
        TokenResponse tokenResponse = keycloakService.getAdminAccessToken(
                null,
                null,
                "refresh_token",
                refreshToken);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setRefreshToken(tokenResponse.getRefreshToken());
        authResponse.setJwt(tokenResponse.getAccessToken());
        authResponse.setMessage("Access token refreshed successfully");
        return authResponse;
    }
}
