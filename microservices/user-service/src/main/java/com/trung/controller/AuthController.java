package com.trung.controller;

import com.trung.payload.dto.LoginDTO;
import com.trung.payload.dto.SignupDTO;
import com.trung.payload.response.AuthResponse;
import com.trung.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(
            @RequestBody SignupDTO request
    ) throws Exception {
        AuthResponse authResponse = authService.signup(request);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginDTO request
    ) throws Exception {
        AuthResponse authResponse = authService.login(
                request.getEmail(),
                request.getPassword());
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @GetMapping("/access-token/refresh-token/{refreshToken}")
    public ResponseEntity<AuthResponse> getAccessToken(
            @PathVariable String refreshToken
    ) throws Exception {
        AuthResponse authResponse = authService.getAccessTokenFromRefreshToken(refreshToken);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
