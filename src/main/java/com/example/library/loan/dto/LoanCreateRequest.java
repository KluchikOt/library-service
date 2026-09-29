package com.example.library.loan.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record LoanCreateRequest(@NotNull(message = "ID книги не должно быть пустым")Long bookId,
                                @NotNull(message = "ID читателя не должно быть пустым")Long readerId,
                                @NotNull(message = "Срок возврата должен быть указан") @Future LocalDate dueDate
                                ) {
}
