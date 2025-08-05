package com.bookstore.bookstore.service;

import com.bookstore.bookstore.dto.BookDTO;
import com.bookstore.bookstore.kafka.BookProducer;
import com.bookstore.bookstore.model.Book;
import com.bookstore.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookProducer bookProducer;

    public BookService(BookRepository bookRepository, BookProducer bookProducer) {
        this.bookRepository = bookRepository;
        this.bookProducer = bookProducer;
    }

    public BookDTO addBook(BookDTO bookDTO) {
        Book book = new Book(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPrice());
        Book saved = bookRepository.save(book);

        bookProducer.sendBook(new BookDTO(saved.getTitle(), saved.getAuthor(), saved.getPrice()));

        return new BookDTO(saved.getTitle(), saved.getAuthor(), saved.getPrice());
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> new BookDTO(book.getTitle(), book.getAuthor(), book.getPrice()))
                .collect(Collectors.toList());
    }
}