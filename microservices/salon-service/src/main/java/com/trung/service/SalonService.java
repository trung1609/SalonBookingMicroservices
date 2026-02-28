package com.trung.service;

import com.trung.modal.Salon;
import com.trung.payload.dto.SalonDTO;
import com.trung.payload.dto.UserDTO;

import java.util.List;

public interface SalonService {
    Salon createSalon(SalonDTO request, UserDTO user);
    Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId);
    List<Salon> getAllSalons();
    Salon getSalonById(Long salonId);
    Salon getSalonByOwnerId(Long ownerId);
    List<Salon> searchSalonByCity(String city);
}
