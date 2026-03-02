package com.trung.service;

import com.trung.modal.ServiceOffering;
import com.trung.payload.dto.CategoryDTO;
import com.trung.payload.dto.SalonDTO;
import com.trung.payload.dto.ServiceDTO;

public interface ServiceOfferingService {
    ServiceOffering createService(SalonDTO salonDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO);
}
