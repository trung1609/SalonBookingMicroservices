package com.trung.controller;

import com.trung.mapper.SalonMapper;
import com.trung.modal.Salon;
import com.trung.payload.dto.SalonDTO;
import com.trung.payload.dto.UserDTO;
import com.trung.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salons")
public class SalonController {

    @Autowired
    private SalonService salonService;

    @PostMapping
    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salonDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon createdSalon = salonService.createSalon(salonDTO, userDTO);
        SalonDTO dto = SalonMapper.toDTO(createdSalon);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SalonDTO> updateSalon(@PathVariable Long id, @RequestBody SalonDTO salonDTO) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        Salon updateSalon = salonService.updateSalon(salonDTO, userDTO, id);
        SalonDTO dto = SalonMapper.toDTO(updateSalon);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<SalonDTO>> getSalons() throws Exception {
        List<Salon> salon = salonService.getAllSalons();
        List<SalonDTO> salonDTOS = salon.stream()
                .map(SalonMapper::toDTO)
                .toList();
        return ResponseEntity.ok(salonDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalonDTO> getSalonById(@PathVariable Long id) throws Exception {
        Salon salon = salonService.getSalonById(id);
        SalonDTO salonDTO = SalonMapper.toDTO(salon);
        return ResponseEntity.ok(salonDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SalonDTO>> searchSalons(@RequestParam("city") String city) throws Exception {
        List<Salon> salons = salonService.searchSalonByCity(city);
        List<SalonDTO> salonDTOS = salons.stream()
                .map(SalonMapper::toDTO)
                .toList();
        return ResponseEntity.ok(salonDTOS);
    }

    @GetMapping("/owner")
    public ResponseEntity<SalonDTO> getSalonByOwnerId() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Salon salon = salonService.getSalonByOwnerId(userDTO.getId());
        SalonDTO salonDTO = SalonMapper.toDTO(salon);
        return ResponseEntity.ok(salonDTO);
    }

}
