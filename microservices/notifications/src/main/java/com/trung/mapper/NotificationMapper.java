package com.trung.mapper;

import com.trung.model.Notification;
import com.trung.payload.dto.BookingDTO;
import com.trung.payload.dto.NotificationDTO;

public class NotificationMapper {
    public static NotificationDTO toDTO(Notification notification, BookingDTO bookingDTO) {
        return NotificationDTO.builder()
                .id(notification.getId())
                .type(notification.getType())
                .description(notification.getDescription())
                .isRead(notification.getIsRead())
                .userId(notification.getUserId())
                .bookingId(notification.getBookingId())
                .salonId(notification.getSalonId())
                .createdAt(notification.getCreatedAt())
                .bookingDTO(bookingDTO)
                .build();
    }
}
