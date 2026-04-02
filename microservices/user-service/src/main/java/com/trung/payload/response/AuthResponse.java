package com.trung.payload.response;

import com.trung.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String jwt;
    private String refreshToken;
    private String message;
    private UserRole role;
}
