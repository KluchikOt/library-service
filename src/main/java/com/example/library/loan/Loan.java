package com.example.library.loan;


import com.example.library.book.Book;
import com.example.library.exception.BookAlreadyReturnedException;
import com.example.library.reader.Reader;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "BookLoans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private LocalDate loanDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = true)
    private LocalDate returnDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", referencedColumnName = "ID")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "reader_id", referencedColumnName = "ID")
    private Reader reader;

    public Loan(Book book, Reader reader, LocalDate dueDate) {
        this.book = book;
        this.reader = reader;
        this.loanDate = LocalDate.now();
        this.dueDate = dueDate;
        this.status = LoanStatus.ON_HANDS;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Loan)) return false;
        if(getId() == null) return false;
        Loan loan = (Loan) o;
        return Objects.equals(getId(), loan.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }

    public void loanReturn() {
        if(status.name().equals("RETURNED")) throw new BookAlreadyReturnedException("Книга уже возвращена");
        this.returnDate = LocalDate.now();
        this.status = LoanStatus.RETURNED;
    }

}
