package com.example.library.reader.dto;

import java.time.LocalDate;

public record ReaderResponse(Long id, String firstName, String lastName, String email, String phoneNumber, LocalDate registrationDate) {
}
