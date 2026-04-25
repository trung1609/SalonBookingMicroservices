package com.trung.messaging;

import com.trung.model.Notification;
import com.trung.payload.dto.NotificationDTO;
import com.trung.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationEventConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues = "notification-queue")
    public void sentNotificationEventConsumer(NotificationDTO notificationDTO) throws Exception {
        if (notificationDTO == null) {
            log.error("Received null notification DTO");
            return;
        }

        log.info("Received notification event: bookingId={}, userId={}, salonId={}, type={}",
                notificationDTO.getBookingId(),
                notificationDTO.getUserId(),
                notificationDTO.getSalonId(),
                notificationDTO.getType());
        if (notificationDTO.getBookingId() == null || notificationDTO.getBookingId() <= 0) {
            log.error("Invalid booking ID in notification: {}", notificationDTO.getBookingId());
            throw new IllegalArgumentException("Booking ID must be valid and greater than 0");
        }
        if (notificationDTO.getUserId() == null || notificationDTO.getUserId() <= 0) {
            log.error("Invalid user ID in notification: {}", notificationDTO.getUserId());
            throw new IllegalArgumentException("User ID must be valid and greater than 0");
        }
        Notification notification = new Notification();
        notification.setBookingId(notificationDTO.getBookingId());
        notification.setUserId(notificationDTO.getUserId());
        notification.setSalonId(notificationDTO.getSalonId());
        notification.setType(notificationDTO.getType());
        notification.setDescription(notificationDTO.getDescription());
        notification.setIsRead(false);

        log.info("Creating notification with bookingId: {}", notification.getBookingId());
        notificationService.createNotification(notification);
        log.info("Notification created successfully for bookingId: {}", notification.getBookingId());
    }
}
