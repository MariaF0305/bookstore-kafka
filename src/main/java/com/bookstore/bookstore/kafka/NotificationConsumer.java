package com.bookstore.bookstore.kafka;

import com.bookstore.bookstore.dto.NotificationDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {
    @KafkaListener(
            topics = "notifications",
            groupId = "notification-group",
            containerFactory = "notificationKafkaListenerContainerFactory"
    )
    public void consume(ConsumerRecord<String, Object> record, Acknowledgment ack) {
        try {
            Object payload = record.value();
            if (!(payload instanceof NotificationDTO n)) {
                throw new IllegalArgumentException("Unexpected payload type: " + payload);
            }
            System.out.println("[NOTIFICATION] " + n.getMessage());
            ack.acknowledge();
        } catch (Exception e) {
            System.err.println("[NOTIFICATION ERROR] " + e.getMessage());
            throw e;
        }
    }
}
