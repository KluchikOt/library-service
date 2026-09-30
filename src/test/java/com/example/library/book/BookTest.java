package com.example.library.book;

import com.example.library.exception.AllBooksOnHandsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book createBook(int totalCopies) {
        return new Book(
                "Война и Мир",
                "Толстой",
                "1234567890",
                2008,
                totalCopies
        );
    }

    @Test
    void bookGiven_decreasesAvailableCopiesByOne() {
        Book book = createBook(5);
        int before = book.getAvailableCopies();

        book.bookGiven();

        assertEquals(before - 1, book.getAvailableCopies());
    }

    @Test
    void bookGiven_whenNoCopiesLeft_throwsAllBooksOnHandsException() {
        Book book = createBook(1);
        book.bookGiven(); // availableCopies = 0

        assertThrows(AllBooksOnHandsException.class, book::bookGiven);
    }

    @Test
    void bookReturned_increasesAvailableCopiesByOne() {
        Book book = createBook(3);
        book.bookGiven(); // availableCopies = 2
        int before = book.getAvailableCopies();

        book.bookReturned();

        assertEquals(before + 1, book.getAvailableCopies());
        assertEquals(3, book.getAvailableCopies());
    }

}