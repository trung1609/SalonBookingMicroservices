package com.trung.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String description;

    private Boolean isRead;

    private Long userId;

    private Long bookingId;

    private Long salonId;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (isRead == null) {
            isRead = false;
        }
    }
}
