package com.trung.mapper;

import com.trung.modal.Salon;
import com.trung.payload.dto.SalonDTO;

public class SalonMapper {

    public static SalonDTO toDTO(Salon salon){
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(salon.getId());
        salonDTO.setName(salon.getName());
        salonDTO.setAddress(salon.getAddress());
        salonDTO.setCity(salon.getCity());
        salonDTO.setPhone(salon.getPhone());
        salonDTO.setEmail(salon.getEmail());
        salonDTO.setImages(salon.getImages());
        salonDTO.setOpenTime(salon.getOpenTime());
        salonDTO.setCloseTime(salon.getCloseTime());
        salonDTO.setOwnerId(salon.getOwnerId());
        return salonDTO;
    }
}
