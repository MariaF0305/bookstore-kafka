package com.bookstore.bookstore.kafka;

import com.bookstore.bookstore.dto.BookDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class BookConsumer {
    @KafkaListener(
            topics = "books",
            groupId = "bookstore-group",
            containerFactory = "bookKafkaListenerContainerFactory"
    )
    public void consume(ConsumerRecord<String, Object> record, Acknowledgment ack) {
        try {
            Object payload = record.value();
            if (!(payload instanceof BookDTO book)) {
                throw new IllegalArgumentException("Unexpected payload type: " + payload);
            }
            System.out.println("[BOOK CONSUMER] Got: " + book.getTitle());
            ack.acknowledge();
        } catch (Exception e) {
            System.err.println("[BOOK CONSUMER] Error: " + e.getMessage());
            throw e;
        }
    }
}
