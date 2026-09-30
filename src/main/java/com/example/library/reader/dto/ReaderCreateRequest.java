package com.example.library.reader.dto;


import jakarta.validation.constraints.*;

public record ReaderCreateRequest(@NotBlank(message = "Имя читателя должно быть указано") @Size(max = 255, message = "Длина не больше 255 символов") String firstName,
                                  @NotBlank(message = "Фамилия читателя должна быть указана") @Size(max = 255, message = "Длина не больше 255 символов") String lastName,
                                  @NotBlank(message = "Email должен быть указан") @Size(max = 255, message = "Длина не больше 255 символов") @Email String email,
                                  @NotNull(message = "Телефон должен быть указан") @Pattern(message = "Номер телефона должен начинаться с +, и содержать 11 цифр", regexp = "^\\+\\d{11}$") String phoneNumber) {
}




