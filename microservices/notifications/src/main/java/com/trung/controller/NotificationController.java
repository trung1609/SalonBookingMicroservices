package com.trung.controller;

import com.trung.mapper.NotificationMapper;
import com.trung.model.Notification;
import com.trung.payload.dto.BookingDTO;
import com.trung.payload.dto.NotificationDTO;
import com.trung.service.NotificationService;
import com.trung.service.client.BookingFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;
    private final BookingFeignClient bookingFeignClient;

    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(
            @RequestBody Notification notification
    ) throws Exception {
        return ResponseEntity.ok(notificationService.createNotification(notification));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByUserId(
            @PathVariable Long userId
    ) throws Exception {
        List<NotificationDTO> notificationDTOS = notificationService.getAllNotificationByUserId(userId)
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

    @PutMapping("/{notificationId}/read")
    public ResponseEntity<NotificationDTO> markAsRead(
            @PathVariable Long notificationId
    ) throws Exception {
        Notification notification = notificationService.markAsRead(notificationId);
        BookingDTO bookingDTO = bookingFeignClient.getBookingById(notification.getBookingId()).getBody();

        return ResponseEntity.ok(NotificationMapper.toDTO(notification, bookingDTO));
    }
}
