package com.trung.mapper;

import com.trung.dto.BookingDTO;
import com.trung.dto.SalonDTO;
import com.trung.dto.ServiceDTO;
import com.trung.dto.UserDTO;
import com.trung.model.Booking;

import java.util.List;
import java.util.Set;

public class BookingMapper {
    public static BookingDTO toDTO(Booking booking, Set<ServiceDTO> serviceDTOS, UserDTO userDTO, SalonDTO salonDTO) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setCustomerId(booking.getCustomerId());
        bookingDTO.setSalonId(booking.getSalonId());
        bookingDTO.setServiceIds(booking.getServiceIds());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setEndTime(booking.getEndTime());
        bookingDTO.setStartTime(booking.getStartTime());
        bookingDTO.setTotalPrice(booking.getTotalPrice());

        bookingDTO.setServices(serviceDTOS);
        bookingDTO.setSalon(salonDTO);
        bookingDTO.setUser(userDTO);
        return bookingDTO;
    }
}
