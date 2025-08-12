package com.bookstore.bookstore.service;

import com.bookstore.bookstore.dto.NotificationDTO;
import com.bookstore.bookstore.dto.OrderDTO;
import com.bookstore.bookstore.kafka.NotificationProducer;
import com.bookstore.bookstore.model.Book;
import com.bookstore.bookstore.model.Order;
import com.bookstore.bookstore.model.User;
import com.bookstore.bookstore.repository.BookRepository;
import com.bookstore.bookstore.repository.OrderRepository;
import com.bookstore.bookstore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final NotificationProducer notificationProducer;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, BookRepository bookRepository, NotificationProducer notificationProducer) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.notificationProducer = notificationProducer;
    }

    public void placeOrder(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Book book = bookRepository.findById(orderDTO.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        Order order = new Order(LocalDateTime.now(), user, book);
        orderRepository.save(order);

        notificationProducer.sendNotification(
                new NotificationDTO("Order placed by user " + user.getUsername() + " for book: " + book.getTitle()));
    }
}
