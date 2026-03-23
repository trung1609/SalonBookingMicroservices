package com.trung.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {
    private String username;
    private Boolean enabled;
    private String email;
    private String firstName;
    private String lastName;
    private List<Credential> credentials = new ArrayList<>();
}
