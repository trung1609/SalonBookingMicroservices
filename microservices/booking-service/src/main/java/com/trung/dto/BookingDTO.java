package com.trung.dto;

import com.trung.domain.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class BookingDTO {
    private Long id;

    private Long salonId;

    private Long customerId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Set<Long> serviceIds;

    private BookingStatus status = BookingStatus.PENDING;
    private Integer totalPrice;
    private Set<ServiceDTO> services;
    private UserDTO user;
    private SalonDTO salon;
}
