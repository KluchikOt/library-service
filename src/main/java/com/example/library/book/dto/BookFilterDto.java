package com.example.library.book.dto;

public record BookFilterDto(
                      String title,
                      String author,
                      String isbn,
                      Integer publicationYear,
                      Boolean availability) {
}
