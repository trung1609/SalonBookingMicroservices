package com.trung.service;

import com.trung.model.ServiceOffering;
import com.trung.payload.dto.CategoryDTO;
import com.trung.payload.dto.SalonDTO;
import com.trung.payload.dto.ServiceDTO;

import java.util.Set;

public interface ServiceOfferingService {
    ServiceOffering createService(SalonDTO salonDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO);

    ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception;

    Set<ServiceOffering> getAllServicesBySalonId(Long salonId, Long categoryId);

    Set<ServiceOffering> getServicesByIds(Set<Long> ids);

    ServiceOffering getServiceById(Long id) throws Exception;
}
