package com.trung.controller;

import com.trung.dto.BookingRequest;
import com.trung.dto.SalonDTO;
import com.trung.dto.ServiceDTO;
import com.trung.dto.UserDTO;
import com.trung.model.Booking;
import com.trung.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    public ResponseEntity<Booking> createBooking(
            @RequestParam Long salonId,
            @RequestBody BookingRequest request) throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(salonId);

        Set<ServiceDTO> serviceDTOS = new HashSet<>();

        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(1L);
        serviceDTO.setPrice(300);
        serviceDTO.setDuration(45);
        serviceDTO.setName("Haircut");
        serviceDTO.setDescription("Haircut for men");

        serviceDTOS.add(serviceDTO);

        Booking booking = bookingService.createBooking(request, userDTO, salonDTO, serviceDTOS);
        return ResponseEntity.ok(booking);
    }
}
