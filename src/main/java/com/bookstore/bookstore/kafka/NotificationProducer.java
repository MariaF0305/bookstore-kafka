package com.bookstore.bookstore.kafka;

import com.bookstore.bookstore.dto.NotificationDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "notifications";

    public NotificationProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(NotificationDTO notificationDTO) {
        kafkaTemplate.send(TOPIC, notificationDTO);
    }
}
