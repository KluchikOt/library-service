package com.example.library.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record BookUpdateRequest(@NotBlank(message = "Название книги не должно быть пустым") @Size(max = 255, message = "Длина не больше 255 символов") String title,
                                @NotBlank(message = "Имя автора должно быть указано") @Size(max = 255, message = "Длина не больше 255 символов") String author,
                                @NotNull(message = "Год публикации должен быть указан") Integer publicationYear,
                                @NotNull(message = "Количество копий должно быть указано") @Positive(message = "Количество копий должно быть больше нуля") Integer totalCopies) {
}
