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
    public Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId) throws Exception {
        Salon existingSalon = salonRepository.findById(salonId).orElse(null);
        if(existingSalon != null && salon.getOwnerId().equals(user.getId())){
            existingSalon.setAddress(salon.getAddress());
            existingSalon.setCity(salon.getCity());
            existingSalon.setEmail(salon.getEmail());
            existingSalon.setName(salon.getName());
            existingSalon.setOwnerId(user.getId());
            existingSalon.setImages(salon.getImages());
            existingSalon.setOpenTime(salon.getOpenTime());
            existingSalon.setCloseTime(salon.getCloseTime());
            existingSalon.setPhone(salon.getPhone());
            return salonRepository.save(existingSalon);
        }
        throw new Exception("Salon not found with id: " + salonId);
    }

    @Override
    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    @Override
    public Salon getSalonById(Long salonId) throws Exception {
        Salon salon =  salonRepository.findById(salonId).orElse(null);
        if (salon == null){
            throw new Exception("Salon not found with id: " + salonId);
        }
        return salon;
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return salonRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {
        return salonRepository.searchSalons(city);
    }
}
