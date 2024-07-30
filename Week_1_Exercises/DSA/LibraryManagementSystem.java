public class Book {
    private int bookId;
    private String title;
    private String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author;
    }
}

import java.util.Arrays;
import java.util.Comparator;

public class BookManager {
    private Book[] bookCollection;
    private int currentSize;

    public BookManager(int capacity) {
        bookCollection = new Book[capacity];
        currentSize = 0;
    }

    public void addBook(Book book) {
        if (currentSize < bookCollection.length) {
            bookCollection[currentSize++] = book;
        } else {
            System.out.println("Array is full. Cannot add more books.");
        }
    }

    public Book searchBookByTitleLinear(String title) {
        for (int i = 0; i < currentSize; i++) {
            if (bookCollection[i].getTitle().equalsIgnoreCase(title)) {
                return bookCollection[i];
            }
        }
        return null;
    }

    public void sortBooksByTitle() {
        Arrays.sort(bookCollection, 0, currentSize, Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
    }

    public Book searchBookByTitleBinary(String title) {
        int low = 0;
        int high = currentSize - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = bookCollection[mid].getTitle().compareToIgnoreCase(title);
            if (comparison == 0) {
                return bookCollection[mid];
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        BookManager library = new BookManager(10);
        library.addBook(new Book(1, "Effective Java", "Joshua Bloch"));
        library.addBook(new Book(2, "Clean Code", "Robert C. Martin"));
        library.addBook(new Book(3, "Design Patterns", "Erich Gamma"));
        library.addBook(new Book(4, "Refactoring", "Martin Fowler"));

        System.out.println("All books in the library:");
        for (int i = 0; i < 4; i++) {
            System.out.println(library.searchBookByTitleLinear("Effective Java"));
        }

        System.out.println("\nSearching for 'Design Patterns' using linear search:");
        Book book = library.searchBookByTitleLinear("Design Patterns");
        if (book != null) {
            System.out.println("Found: " + book);
        } else {
            System.out.println("Book not found.");
        }

        library.sortBooksByTitle();

        System.out.println("\nSearching for 'Clean Code' using binary search:");
        book = library.searchBookByTitleBinary("Clean Code");
        if (book != null) {
            System.out.println("Found: " + book);
        } else {
            System.out.println("Book not found.");
        }
    }
}
