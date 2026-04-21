package com.trung.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {
    private Long id;

    private String name;

    private String description;

    private int price;

    private int duration;

    private Long salonId;

    private Long categoryId;

    private String image;
}
