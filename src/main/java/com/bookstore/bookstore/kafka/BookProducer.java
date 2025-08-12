package com.bookstore.bookstore.kafka;

import com.bookstore.bookstore.dto.BookDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "books";

    public BookProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendBook(BookDTO bookDTO) {
        kafkaTemplate.send(TOPIC, bookDTO);
    }
}
