package com.example.bookstoreapi.service;

import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Find a book by its ID.
     *
     * @param id the ID of the book
     * @return the book if found, otherwise null
     */
    public Book getBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }

    /**
     * Save a new book or update an existing one.
     *
     * @param book the book to save
     * @return the saved book
     */
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Retrieve all books from the repository.
     *
     * @return a list of all books
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Delete a book by its ID.
     *
     * @param id the ID of the book to delete
     */
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
    public Book createBook(Book book) {
        // Logic to save the book and return the saved instance
        // For example:
        return bookRepository.save(book);
    }
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book updateBook(Long id, Book updatedBook) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        updatedBook.setId(id);
        return bookRepository.save(updatedBook);
    }
    public boolean deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(id);
        return false;
    }
}
