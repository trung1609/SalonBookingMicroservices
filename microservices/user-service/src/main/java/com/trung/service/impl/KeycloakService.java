package com.trung.service.impl;

import com.trung.payload.dto.Credential;
import com.trung.payload.dto.SignupDTO;
import com.trung.payload.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class KeycloakService {

    private static final String KEYCLOAK_BASE_URL = "http://localhost:5000";
    private static final String KEYCLOAK_ADMIN_API = KEYCLOAK_BASE_URL + "/admin/realms/master/users";
    private static final String TOKEN_URL = KEYCLOAK_BASE_URL + "/realms/master/protocol/openid-connect/token";
    private static final String CLIENT_ID = "salon-booking-client";
    private static final String CLIENT_SECRET = "dzBHjfyG0QkIvJhnvoZZgQXVNHTXUPYn";
    private static final String GRANT_TYPE = "password";
    private static final String scope = "openid profile email";
    private static final String username = "trungadmin";
    private static final String password = "123456";
    private static final String clientId = "ec67d59b-323c-444b-b08e-ee237819bdd1";

    private final RestTemplate restTemplate;
    public void createUser(SignupDTO signupDTO) throws Exception{

        String ACCESS_TOKEN = "";

        Credential credential = new Credential();
        credential.setTemporary(false);
        credential.setType("password");
        credential.setValue(signupDTO.getPassword());

        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(signupDTO.getUsername());
        userRequest.setEmail(signupDTO.getEmail());
        userRequest.setFirstName(signupDTO.getFirstName());
        userRequest.setLastName(signupDTO.getLastName());
        userRequest.setEnabled(true);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(ACCESS_TOKEN);

        HttpEntity<UserRequest> requestEntity = new HttpEntity<>(userRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                KEYCLOAK_ADMIN_API,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.CREATED){
            System.out.println("User created successfully");
        }
    }

    public TokenResponse getAdminAccessToken(String username,
                                             String password,
                                             String grantType,
                                             String refreshToken) {
        return null;
    }

    public KeycloakRole getRoleByName(String clientId,
                                      String token,
                                      String role){
        return null;
    }

    public KeycloakUserDTO fetchFirstUserByUsername(String username, String token){
        return null;
    }
}
