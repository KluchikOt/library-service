package com.example.library.book.dto;


import jakarta.validation.constraints.*;


public record BookCreateRequest(@NotBlank(message = "Название книги не должно быть пустым") String title,
                                @NotBlank(message = "Имя автора должно быть указано") String author,
                                @NotNull(message = "ISBN должен быть указан") @Pattern(message = "Код должен содержать 10 либо 13 цифр", regexp = "^(\\d{10}|\\d{13})$") String isbn,
                                @NotNull(message = "Год публикации должен быть указан") Integer publicationYear,
                                @NotNull(message = "Количество копий должно быть указано") @Positive(message = "Количество копий должно быть больше нуля") Integer totalCopies
) {
}

