package com.example.library.reader.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ReaderUpdateRequest(
        @NotBlank(message = "Фамилия читателя должна быть указана") String lastName,
        @NotBlank(message = "Email должен быть указан") @Email String email,
        @NotNull(message = "Телефон должен быть указан") @Pattern(message = "Номер телефона должен начинаться с +, и содержать 11 цифр", regexp = "^\\+\\d{11}$") String phoneNumber) {
}
