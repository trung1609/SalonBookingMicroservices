package com.trung.controller;

import com.trung.mapper.SalonMapper;
import com.trung.model.Salon;
import com.trung.payload.dto.SalonDTO;
import com.trung.payload.dto.UserDTO;
import com.trung.service.SalonService;
import com.trung.service.client.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salons")
@RequiredArgsConstructor
public class SalonController {
    private final SalonService salonService;
    private final UserFeignClient userFeignClient;

    @PostMapping
    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salonDTO,
                                                @RequestHeader("Authorization") String jwt) throws Exception {
        UserDTO userDTO = userFeignClient.getUserProfile(jwt).getBody();
        Salon createdSalon = salonService.createSalon(salonDTO, userDTO);
        SalonDTO dto = SalonMapper.toDTO(createdSalon);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalonDTO> updateSalon(@PathVariable Long id,
                                                @RequestBody SalonDTO salonDTO,
                                                @RequestHeader("Authorization") String jwt) throws Exception {
        UserDTO userDTO = userFeignClient.getUserProfile(jwt).getBody();

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
    public ResponseEntity<SalonDTO> getSalonByOwnerId(@RequestHeader("Authorization") String jwt) throws Exception {
        UserDTO userDTO = userFeignClient.getUserProfile(jwt).getBody();

        if (userDTO == null) {
            throw new Exception("User not found from JWT ...");
        }

        Salon salon = salonService.getSalonByOwnerId(userDTO.getId());
        SalonDTO salonDTO = SalonMapper.toDTO(salon);
        return ResponseEntity.ok(salonDTO);
    }

}
