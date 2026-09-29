package com.example.library.loan.dto;


import com.example.library.loan.LoanStatus;

import java.time.LocalDate;

public record LoanResponse(Long id, Long bookId, Long readerId, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate, LoanStatus status) {
}
