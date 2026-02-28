package com.trung.service.impl;

import com.trung.modal.Salon;
import com.trung.payload.dto.SalonDTO;
import com.trung.payload.dto.UserDTO;
import com.trung.repository.SalonRepository;
import com.trung.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalonServiceImpl implements SalonService {

    @Autowired
    private SalonRepository salonRepository;

    @Override
    public Salon createSalon(SalonDTO request, UserDTO user) {
        Salon salon = new Salon();
        salon.setAddress(request.getAddress());
        salon.setCity(request.getCity());
        salon.setEmail(request.getEmail());
        salon.setName(request.getName());
        salon.setOwnerId(user.getId());
        salon.setImages(request.getImages());
        salon.setOpenTime(request.getOpenTime());
        salon.setCloseTime(request.getCloseTime());
        salon.setPhone(request.getPhone());
        return salonRepository.save(salon);
    }

    @Override
    public Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId) {
        return null;
    }

    @Override
    public List<Salon> getAllSalons() {
        return List.of();
    }

    @Override
    public Salon getSalonById(Long salonId) {
        return null;
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return null;
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {
        return List.of();
    }
}
