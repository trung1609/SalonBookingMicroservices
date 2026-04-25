package com.trung.messaging;

import com.trung.payload.dto.NotificationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationEventProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sentNotification(Long bookingId,
                                      Long userId,
                                      Long salonId) {
        log.info("Sending notification: bookingId={}, userId={}, salonId={}", bookingId, userId, salonId);

        if (bookingId == null || bookingId <= 0) {
            log.error("Invalid booking ID for notification: {}", bookingId);
            throw new IllegalArgumentException("Booking ID must be valid and greater than 0");
        }
        if (userId == null || userId <= 0) {
            log.error("Invalid user ID for notification: {}", userId);
            throw new IllegalArgumentException("User ID must be valid and greater than 0");
        }

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setBookingId(bookingId);
        notificationDTO.setUserId(userId);
        notificationDTO.setSalonId(salonId);
        notificationDTO.setDescription("New booking got confirmed.");
        notificationDTO.setType("BOOKING");

        log.info("Publishing notification to queue with details: {}", notificationDTO);
        rabbitTemplate.convertAndSend("notification-queue", notificationDTO);
        log.info("Notification published successfully");
    }
}
