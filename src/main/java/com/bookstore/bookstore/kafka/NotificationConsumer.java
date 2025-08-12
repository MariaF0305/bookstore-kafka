package com.bookstore.bookstore.kafka;

import com.bookstore.bookstore.dto.NotificationDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {
    @KafkaListener(topics = "notifications", groupId = "notification-group")
    public void consume(NotificationDTO notificationDTO) {
        System.out.println("Notification received: " + notificationDTO.getMessage());
    }
}
