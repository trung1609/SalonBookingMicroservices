package com.trung.service;

import com.trung.model.Notification;
import com.trung.payload.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {
    NotificationDTO createNotification(Notification notification) throws Exception;
    List<Notification> getAllNotificationByUserId(Long userId);
    List<Notification> getAllNotificationBySalonId(Long salonId);
    Notification markAsRead(Long notificationId) throws Exception;
}
