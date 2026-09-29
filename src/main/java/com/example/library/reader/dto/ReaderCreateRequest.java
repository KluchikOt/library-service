package com.example.library.reader.dto;


import jakarta.validation.constraints.*;

public record ReaderCreateRequest(@NotBlank(message = "Имя читателя должно быть указано") String firstName,
                                  @NotBlank(message = "Фамилия читателя должна быть указана") String lastName,
                                  @NotBlank(message = "Email должен быть указан") @Email String email,
                                  @NotNull(message = "Телефон должен быть указан") @Pattern(message = "Номер телефона должен начинаться с +, и содержать 11 цифр", regexp = "^\\+\\d{11}$") String phoneNumber) {
}




