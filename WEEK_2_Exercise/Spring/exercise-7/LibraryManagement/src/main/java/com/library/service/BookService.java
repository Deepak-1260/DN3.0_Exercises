package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    // Constructor injection
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Setter injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Method to delegate book display functionality to the repository
    public void displayBooks() {
        System.out.println("Service: Delegating to repository...");
        bookRepository.displayBooks();
    }
}
