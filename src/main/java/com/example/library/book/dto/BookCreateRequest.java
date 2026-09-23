package com.example.library.book.dto;


import jakarta.validation.constraints.*;



public record BookCreateRequest(@NotBlank String title,
                         @NotBlank String author,
                         @NotNull @Pattern(regexp = "^(\\d{10}|\\d{13})$")  String isbn,
                         @NotNull Integer publicationYear,
                         @NotNull @Positive Integer totalCopies
) {
}

