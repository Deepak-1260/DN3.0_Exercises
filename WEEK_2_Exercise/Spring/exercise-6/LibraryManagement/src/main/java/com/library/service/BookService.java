package com.library.service;

import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    // Constructor-based injection
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Method to delegate book display functionality to the repository
    public void displayBooks() {
        System.out.println("Service: Delegating to repository...");
        bookRepository.displayBooks();
    }
}

