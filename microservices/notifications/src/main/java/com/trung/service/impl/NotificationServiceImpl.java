package com.trung.service.impl;

import com.trung.mapper.NotificationMapper;
import com.trung.model.Notification;
import com.trung.payload.dto.BookingDTO;
import com.trung.payload.dto.NotificationDTO;
import com.trung.repository.NotificationRepository;
import com.trung.service.NotificationService;
import com.trung.service.client.BookingFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final BookingFeignClient bookingFeignClient;

    @Override
    public NotificationDTO createNotification(Notification notification) throws Exception {
        Notification savedNotification = notificationRepository.save(notification);

        BookingDTO bookingDTO = bookingFeignClient.getBookingById(savedNotification.getBookingId()).getBody();

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
                }).orElseThrow(()-> new Exception("Notification not found with id: " + notificationId));
    }
}
