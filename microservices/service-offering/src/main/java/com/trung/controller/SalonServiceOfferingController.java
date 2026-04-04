package com.trung.controller;

import com.trung.model.ServiceOffering;
import com.trung.payload.dto.CategoryDTO;
import com.trung.payload.dto.SalonDTO;
import com.trung.payload.dto.ServiceDTO;
import com.trung.service.ServiceOfferingService;
import com.trung.service.client.CategoryFeignClient;
import com.trung.service.client.SalonFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service-offering/salon-owner")
@RequiredArgsConstructor
public class SalonServiceOfferingController {
    private final ServiceOfferingService serviceOfferingService;
    private final SalonFeignClient salonFeignClient;
    private final CategoryFeignClient categoryFeignClient;

    @PostMapping
    public ResponseEntity<ServiceOffering> createService(
            @RequestBody ServiceDTO serviceDTO,
            @RequestHeader("Authorization") String jwt) throws Exception {
        SalonDTO salonDTO = salonFeignClient.getSalonByOwnerId(jwt).getBody();
        CategoryDTO categoryDTO = categoryFeignClient.getCategoriesByIdAndSalon(
                serviceDTO.getCategoryId(),
                salonDTO.getId()).getBody();
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
