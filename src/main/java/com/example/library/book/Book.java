package com.example.library.book;

import com.example.library.exception.AllBooksOnHandsException;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(
        name = "Books",
        uniqueConstraints = @UniqueConstraint(name = "uq_books_isbn", columnNames = {"isbn"})
)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, length = 13)
    private String isbn;

    @Column(nullable = false)
    private int publicationYear;

    @Column(nullable = false)
    private int totalCopies;

    @Column(nullable = false)
    private int availableCopies;


    public Book(String title, String author, String isbn, int publicationYear, int totalCopies) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getIsbn(), book.getIsbn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIsbn());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationYear=" + publicationYear +
                ", totalCopies=" + totalCopies +
                ", availableCopies=" + availableCopies +
                '}';
    }

    public void updateData(String newTitle, String newAuthor, int newPublicationYear, int newTotalCopies) {
        int booksOnShelf = availableCopies + (newTotalCopies - totalCopies);
        if(booksOnShelf < 0) {
            throw new AllBooksOnHandsException("Нельзя уменьшить общее количество копий ниже числа выданных книг");
        }
        title = newTitle;
        author = newAuthor;
        publicationYear = newPublicationYear;
        availableCopies = booksOnShelf;
        totalCopies = newTotalCopies;

    }

    public void bookReturned() {
        if(availableCopies + 1 > totalCopies) throw new IllegalStateException();
        this.availableCopies += 1;
    }

    public void bookGiven() {
        if(availableCopies == 0) {
            throw new AllBooksOnHandsException("Книг больше не осталось, зайдите позже");
        }
        this.availableCopies -= 1;
    }


}
