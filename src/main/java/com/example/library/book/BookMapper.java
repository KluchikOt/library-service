package com.example.library.book;

import com.example.library.book.dto.BookCreateRequest;
import com.example.library.book.dto.BookResponse;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public Book toEntity(BookCreateRequest request) {

        return new Book(request.title(), request.author(), request.isbn(), request.publicationYear(), request.totalCopies());

    }

    public BookResponse toResponse(Book book) {

        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPublicationYear(), book.getTotalCopies(), book.getAvailableCopies());
    }
}

