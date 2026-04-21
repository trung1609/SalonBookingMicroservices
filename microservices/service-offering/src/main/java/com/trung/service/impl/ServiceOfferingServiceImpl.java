package com.trung.service.impl;

import com.trung.model.ServiceOffering;
import com.trung.payload.dto.CategoryDTO;
import com.trung.payload.dto.SalonDTO;
import com.trung.payload.dto.ServiceDTO;
import com.trung.repository.ServiceOfferingRepository;
import com.trung.service.ServiceOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    @Autowired
    private ServiceOfferingRepository serviceOfferingRepository;

    @Override
    public ServiceOffering createService(SalonDTO salonDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO) {
        ServiceOffering serviceOffering = new ServiceOffering();
        serviceOffering.setImage(serviceDTO.getImage());
        serviceOffering.setSalonId(salonDTO.getId());
        serviceOffering.setCategoryId(categoryDTO.getId());
        serviceOffering.setName(serviceDTO.getName());
        serviceOffering.setDescription(serviceDTO.getDescription());
        serviceOffering.setPrice(serviceDTO.getPrice());
        serviceOffering.setDuration(serviceDTO.getDuration());
        return serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception {
        ServiceOffering existingService = serviceOfferingRepository.findById(serviceId).orElse(null);
        if (existingService == null) {
            throw new Exception("Service not exist with id: " + serviceId);
        }
        existingService.setImage(service.getImage());
        existingService.setName(service.getName());
        existingService.setDescription(service.getDescription());
        existingService.setPrice(service.getPrice());
        existingService.setDuration(service.getDuration());
        return serviceOfferingRepository.save(existingService);
    }

    @Override
    public Set<ServiceOffering> getAllServicesBySalonId(Long salonId, Long categoryId) {
        Set<ServiceOffering> services = serviceOfferingRepository.findBySalonId(salonId);
        if (categoryId != null) {
            services = services.stream().filter((service) -> service.getCategoryId() != null &&
                            service.getCategoryId().equals(categoryId))
                    .collect(Collectors.toSet());
        }
        return services;
    }

    @Override
    public Set<ServiceOffering> getServicesByIds(Set<Long> ids) {
        List<ServiceOffering> serviceOfferings = serviceOfferingRepository.findAllById(ids);
        return new HashSet<>(serviceOfferings);
    }

    @Override
    public ServiceOffering getServiceById(Long id) throws Exception {
        ServiceOffering serviceOffering = serviceOfferingRepository.findById(id).orElse(null);
        if (serviceOffering == null) {
            throw new Exception("Service not found with id: " + id);
        }
        return serviceOffering;
    }
}
