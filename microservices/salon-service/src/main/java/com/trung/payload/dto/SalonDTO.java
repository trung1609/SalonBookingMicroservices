package com.trung.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalonDTO {
    private Long id;

    private String name;

    private List<String> images;


    private String address;


    private String phone;


    private String email;


    private String city;


    private Long ownerId;

    private LocalTime openTime;


    private LocalTime closeTime;
}
