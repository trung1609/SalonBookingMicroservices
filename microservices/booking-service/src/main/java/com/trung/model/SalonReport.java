package com.trung.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalonReport {
    private Long salonId;
    private String salonName;
    private int totalEarning;
    private Integer totalBooking;
    private Integer cancelledBooking;
    private Double totalRefund;
}
