package com.trung.controller;

import com.trung.domain.BookingStatus;
import com.trung.domain.PaymentMethod;
import com.trung.dto.*;
import com.trung.mapper.BookingMapper;
import com.trung.model.Booking;
import com.trung.model.SalonReport;
import com.trung.service.BookingService;
import com.trung.service.client.PaymentFeignClient;
import com.trung.service.client.SalonFeignClient;
import com.trung.service.client.ServiceOfferingFeignClient;
import com.trung.service.client.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final SalonFeignClient salonFeignClient;
    private final UserFeignClient userFeignClient;
    private final ServiceOfferingFeignClient serviceOfferingFeignClient;
    private final PaymentFeignClient paymentFeignClient;

    @PostMapping
    public ResponseEntity<PaymentLinkResponse> createBooking(
            @RequestParam Long salonId,
            @RequestParam PaymentMethod paymentMethod,
            @RequestBody BookingRequest request,
            @RequestHeader("Authorization") String jwt) throws Exception {

        UserDTO userDTO = userFeignClient.getUserProfile(jwt).getBody();

        SalonDTO salonDTO = salonFeignClient.getSalonById(salonId).getBody();

        Set<ServiceDTO> serviceDTOSet = serviceOfferingFeignClient.getServicesByIds(request.getServiceIds()).getBody();

        if (serviceDTOSet.isEmpty()) {
            throw new Exception("No services found for the given IDs");
        }

        Booking booking = bookingService.createBooking(request, userDTO, salonDTO, serviceDTOSet);

        PaymentLinkResponse response = paymentFeignClient.createPaymentLink(
                BookingMapper.toDTO(booking, serviceDTOSet, userDTO, salonDTO),
                paymentMethod,
                jwt).getBody();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDTO>> getBookingByCustomer(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        UserDTO userDTO = userFeignClient.getUserProfile(jwt).getBody();

        if (userDTO == null || userDTO.getId() == null) {
            throw new Exception("User not found from JWT ...");
        }

        List<Booking> bookings = bookingService.getBookingsByCustomer(userDTO.getId());
        return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    @GetMapping("/salon")
    public ResponseEntity<Set<BookingDTO>> getBookingBySalon(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        SalonDTO salonDTO = salonFeignClient.getSalonByOwnerId(jwt).getBody();

        if (salonDTO == null || salonDTO.getId() == null) {
            throw new Exception("Salon not found from JWT ...");
        }

        List<Booking> bookings = bookingService.getBookingsBySalon(salonDTO.getId());
        return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    private Set<BookingDTO> getBookingDTOs(List<Booking> bookings) {
        return bookings.stream()
                .map(booking -> {
                    Set<ServiceDTO> serviceDTO = serviceOfferingFeignClient.getServicesByIds(booking.getServiceIds()).getBody();
                    UserDTO userDTO = null;
                    SalonDTO salonDTO = null;
                    try {
                        salonDTO = salonFeignClient.getSalonById(booking.getSalonId()).getBody();
                        userDTO = userFeignClient.getUserById(booking.getCustomerId()).getBody();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return BookingMapper.toDTO(booking, serviceDTO, userDTO, salonDTO);
                })
                .collect(Collectors.toSet());
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) throws Exception {
        Booking booking = bookingService.getBookingById(bookingId);
        Set<ServiceDTO> serviceDTO = serviceOfferingFeignClient.getServicesByIds(booking.getServiceIds()).getBody();
        UserDTO customer = userFeignClient.getUserById(booking.getCustomerId()).getBody();
        SalonDTO salonDTO = salonFeignClient.getSalonById(booking.getSalonId()).getBody();
        return ResponseEntity.ok(BookingMapper.toDTO(booking, serviceDTO, customer, salonDTO));
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(@PathVariable Long bookingId,
                                                          @RequestParam BookingStatus status) throws Exception {
        Booking booking = bookingService.updateBooking(bookingId, status);
        Set<ServiceDTO> serviceDTO = serviceOfferingFeignClient.getServicesByIds(booking.getServiceIds()).getBody();
        UserDTO customer = userFeignClient.getUserById(booking.getCustomerId()).getBody();
        SalonDTO salonDTO = salonFeignClient.getSalonById(booking.getSalonId()).getBody();
        return ResponseEntity.ok(BookingMapper.toDTO(booking, serviceDTO, customer, salonDTO));
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
    public ResponseEntity<SalonReport> getSalonReport(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        SalonDTO salonDTO = salonFeignClient.getSalonByOwnerId(jwt).getBody();

        if (salonDTO == null || salonDTO.getId() == null) {
            throw new Exception("Salon not found from JWT ...");
        }

        SalonReport report = bookingService.getSalonReport(salonDTO.getId());
        return ResponseEntity.ok(report);
    }

}
