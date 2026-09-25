package com.example.library.book;

import jakarta.persistence.*;
import lombok.*;



@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = "id")
@ToString
@Table(
        name = "books",
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

}
