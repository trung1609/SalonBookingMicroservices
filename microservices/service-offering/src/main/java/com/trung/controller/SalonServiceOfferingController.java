package com.trung.controller;

import com.trung.model.ServiceOffering;
import com.trung.payload.dto.CategoryDTO;
import com.trung.payload.dto.SalonDTO;
import com.trung.payload.dto.ServiceDTO;
import com.trung.service.ServiceOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service-offering/salon-owner")
public class SalonServiceOfferingController {
    @Autowired
    private ServiceOfferingService serviceOfferingService;


    @PostMapping
    public ResponseEntity<ServiceOffering> createService(
            @RequestBody ServiceDTO serviceDTO) {
        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(serviceDTO.getCategoryId());
        ServiceOffering serviceOfferings = serviceOfferingService.createService(salonDTO, serviceDTO, categoryDTO);
        return ResponseEntity.ok(serviceOfferings);
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<ServiceOffering> updateService(
            @PathVariable Long serviceId,
            @RequestBody ServiceOffering service) throws Exception {
        ServiceOffering serviceOfferings = serviceOfferingService.updateService(serviceId, service);
        return ResponseEntity.ok(serviceOfferings);
    }
}
