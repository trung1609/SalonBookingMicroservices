package com.trung.service.impl;

import com.trung.mapper.NotificationMapper;
import com.trung.model.Notification;
import com.trung.payload.dto.BookingDTO;
import com.trung.payload.dto.NotificationDTO;
import com.trung.repository.NotificationRepository;
import com.trung.service.NotificationService;
import com.trung.service.client.BookingFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final BookingFeignClient bookingFeignClient;

    @Override
    public NotificationDTO createNotification(Notification notification) throws Exception {
        if (notification.getCreatedAt() == null) {
            notification.setCreatedAt(LocalDateTime.now());
        }

        // Validate required fields
        if (notification.getBookingId() == null || notification.getBookingId() <= 0) {
            throw new IllegalArgumentException("Booking ID is required for notification");
        }
        if (notification.getUserId() == null || notification.getUserId() <= 0) {
            throw new IllegalArgumentException("User ID is required for notification");
        }

        Notification savedNotification = notificationRepository.save(notification);

        BookingDTO bookingDTO = null;
        try {
            if (savedNotification.getBookingId() != null && savedNotification.getBookingId() > 0) {
                log.info("Fetching booking details for bookingId: {}", savedNotification.getBookingId());
                bookingDTO = bookingFeignClient.getBookingById(savedNotification.getBookingId()).getBody();
            }
        } catch (Exception e) {
            log.warn("Failed to fetch booking details for booking ID: {}. Error: {}",
                    savedNotification.getBookingId(), e.getMessage());
        }

        NotificationDTO notificationDTO = NotificationMapper.toDTO(savedNotification, bookingDTO);
        return notificationDTO;
    }

    @Override
    public List<Notification> getAllNotificationByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    @Override
    public List<Notification> getAllNotificationBySalonId(Long salonId) {
        return notificationRepository.findBySalonId(salonId);
    }

    @Override
    public Notification markAsRead(Long notificationId) throws Exception {
        return notificationRepository.findById(notificationId).map(
                notification -> {
                    notification.setIsRead(true);
                    return notificationRepository.save(notification);
                }).orElseThrow(() -> new Exception("Notification not found with id: " + notificationId));
    }
}
