package com.trung.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KeycloakRole {
    private String id;
    private String name;
    private String description;
    private boolean clientRole;
    private boolean composite;
    private String containerId;
    private Map<String, Object> attributes;
}
