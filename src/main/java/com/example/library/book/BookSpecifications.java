package com.example.library.book;

import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {

    private BookSpecifications() {
    }

    public static Specification<Book> hasTitle(String title) {
        return (root, query, cb) ->
                title == null ? cb.conjunction() : cb.equal(root.get("title"), title);
    }

    public static Specification<Book> hasAuthor(String author) {
        return (root, query, cb) ->
                author == null ? cb.conjunction() : cb.equal(root.get("author"), author);
    }

    public static Specification<Book> hasIsbn(String isbn) {
        return (root, query, cb) ->
                isbn == null ? cb.conjunction() : cb.equal(root.get("isbn"), isbn);
    }

    public static Specification<Book> hasYear(Integer publicationYear) {
        return (root, query, cb) ->
                publicationYear == null ? cb.conjunction() : cb.equal(root.get("publicationYear"), publicationYear);
    }

    public static Specification<Book> hasAvailability(Boolean availability) {
        return (root, query, cb) -> {
            if (availability == null) {
                return cb.conjunction();
            }
            return availability
                    ? cb.greaterThan(root.get("availableCopies"), 0)
                    : cb.lessThanOrEqualTo(root.get("availableCopies"), 0);
        };
    }
}