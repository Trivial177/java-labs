package edu.course.lab04;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BookTest {

    @Test
    void findsValueByAnotherBookWithSameIsbn() {
        HashMap<Book, String> books = new HashMap<>();

        Book firstBook = new Book("123", "First title");
        Book secondBook = new Book("123", "Second title");

        books.put(firstBook, "value");

        assertEquals("value", books.get(secondBook));
    }

    @Test
    void booksWithDifferentIsbnAreNotEqual() {
        Book firstBook = new Book("123", "Title");
        Book secondBook = new Book("456", "Title");

        assertNotEquals(firstBook, secondBook);
    }
}