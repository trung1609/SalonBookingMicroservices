package com.trung.controller;

import com.trung.mapper.NotificationMapper;
import com.trung.payload.dto.BookingDTO;
import com.trung.payload.dto.NotificationDTO;
import com.trung.service.NotificationService;
import com.trung.service.client.BookingFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications/salon-owner")
@RequiredArgsConstructor
public class SalonNotificationController {

    private final NotificationService notificationService;
    private final BookingFeignClient bookingFeignClient;

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<NotificationDTO>> getNotificationsBySalonId(
            @PathVariable Long salonId
    ) throws Exception {
        List<NotificationDTO> notificationDTOS = notificationService.getAllNotificationBySalonId(salonId)
                .stream()
                .map(notification -> {
                    BookingDTO bookingDTO = null;
                    try {
                        bookingDTO = bookingFeignClient.getBookingById(notification.getBookingId()).getBody();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return NotificationMapper.toDTO(notification, bookingDTO);
                }).toList();

        return ResponseEntity.ok(notificationDTOS);
    }
}
