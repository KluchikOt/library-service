package com.example.library.loan;

import com.example.library.book.Book;
import com.example.library.exception.BookAlreadyReturnedException;
import com.example.library.reader.Reader;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    private Book createBook() {
        return new Book(
                "Война и мир",
                "Толстой",
                "1234567890",
                2008,
                3
        );
    }

    private Reader createReader() {
        return new Reader(
                "Иван",
                "Иванов",
                "ivan@example.com",
                "+1234567890"
        );
    }

    private Loan createLoan() {
        return new Loan(
                createBook(),
                createReader(),
                LocalDate.now().plusDays(14)
        );
    }

    @Test
    void constructor_setsLoanDateTodayAndStatusOnHands() {
        Loan loan = createLoan();

        assertEquals(LocalDate.now(), loan.getLoanDate());
        assertEquals(LoanStatus.ON_HANDS, loan.getStatus());
        assertNull(loan.getReturnDate());
    }

    @Test
    void loanReturn_setsReturnDateAndStatusReturned() {
        Loan loan = createLoan();

        loan.loanReturn();

        assertEquals(LocalDate.now(), loan.getReturnDate());
        assertEquals(LoanStatus.RETURNED, loan.getStatus());
    }

    @Test
    void loanReturn_whenAlreadyReturned_throwsBookAlreadyReturnedException() {
        Loan loan = createLoan();
        loan.loanReturn();

        assertThrows(BookAlreadyReturnedException.class, loan::loanReturn);
    }
}