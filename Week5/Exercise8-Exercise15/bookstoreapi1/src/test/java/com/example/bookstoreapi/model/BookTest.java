package com.example.bookstoreapi.model;

import org.junit.jupiter.api.Test;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {

    private Validator validator;

    public BookTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testBookTitleNotNull() {
        Book book = new Book();
        book.setAuthor("Author Name");

        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertEquals(1, violations.size());

        ConstraintViolation<Book> violation = violations.iterator().next();
        assertEquals("Title must not be null", violation.getMessage());
    }
}
