package com.bookstore.bookstore.kafka;

import com.bookstore.bookstore.dto.BookDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BookConsumer {
    @KafkaListener(topics = "books", groupId = "bookstore-group")
    public void consume(BookDTO bookDTO) {
        System.out.println("Consumed message: " + bookDTO);
    }
}
