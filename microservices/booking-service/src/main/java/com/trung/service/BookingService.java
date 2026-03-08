package com.trung.service;

import com.trung.domain.BookingStatus;
import com.trung.dto.BookingRequest;
import com.trung.dto.SalonDTO;
import com.trung.dto.ServiceDTO;
import com.trung.dto.UserDTO;
import com.trung.model.Booking;
import com.trung.model.SalonReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {
    Booking createBooking(BookingRequest booking,
                          UserDTO userDTO,
                          SalonDTO salonDTO,
                          Set<ServiceDTO> servicesDTO) throws Exception;

    List<Booking> getBookingsByCustomer(Long customerId);
    List<Booking> getBookingsBySalon(Long salonId);
    Booking getBookingById(Long bookingId) throws Exception;
    Booking updateBooking(Long bookingId, BookingStatus status) throws Exception;
    List<Booking> getBookingsByDate(LocalDate date, Long salonId);
    SalonReport getSalonReport(Long salonId);
}
