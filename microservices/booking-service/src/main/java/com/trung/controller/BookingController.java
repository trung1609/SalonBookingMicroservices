package com.trung.controller;

import com.trung.domain.BookingStatus;
import com.trung.dto.*;
import com.trung.mapper.BookingMapper;
import com.trung.model.Booking;
import com.trung.model.SalonReport;
import com.trung.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestParam Long salonId,
            @RequestBody BookingRequest request) throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(salonId);
        salonDTO.setOpenTime(LocalTime.now());
        salonDTO.setCloseTime(LocalTime.now().plusHours(12));

        Set<ServiceDTO> serviceDTOS = new HashSet<>();

        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(1L);
        serviceDTO.setPrice(300);
        serviceDTO.setDuration(45);
        serviceDTO.setName("Haircut");

        serviceDTOS.add(serviceDTO);

        Booking booking = bookingService.createBooking(request, userDTO, salonDTO, serviceDTOS);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDTO>> getBookingByCustomer() {
        List<Booking> bookings = bookingService.getBookingsByCustomer(1L);
        return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    @GetMapping("/salon")
    public ResponseEntity<Set<BookingDTO>> getBookingBySalon() {
        List<Booking> bookings = bookingService.getBookingsBySalon(1L);
        return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    private Set<BookingDTO> getBookingDTOs(List<Booking> bookings) {
        return bookings.stream()
                .map(BookingMapper::toDTO)
                .collect(Collectors.toSet());
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) throws Exception {
        Booking booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(@PathVariable Long bookingId,
                                                          @RequestParam BookingStatus status) throws Exception {
        Booking booking = bookingService.updateBooking(bookingId, status);
        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }

    @GetMapping("/slots/salon/{salonId}/date")
    public ResponseEntity<List<BookingSlotDTO>> getBookedSlot(
            @PathVariable Long salonId,
            @RequestParam(required = false) LocalDate date) throws Exception {
        List<Booking> bookings = bookingService.getBookingsByDate(date, salonId);

        List<BookingSlotDTO> bookingSlotDTOS = bookings.stream()
                .map(booking -> {
                    BookingSlotDTO bookingSlotDTO = new BookingSlotDTO();
                    bookingSlotDTO.setStartTime(booking.getStartTime());
                    bookingSlotDTO.setEndTime(booking.getEndTime());
                    return bookingSlotDTO;
                })
                .toList();
        return ResponseEntity.ok(bookingSlotDTOS);
    }

    @GetMapping("/report")
    public ResponseEntity<SalonReport> getSalonReport() throws Exception {
        SalonReport report = bookingService.getSalonReport(1L);
        return ResponseEntity.ok(report);
    }

}
