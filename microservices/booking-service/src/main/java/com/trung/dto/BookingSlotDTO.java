package com.trung.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class BookingSlotDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
