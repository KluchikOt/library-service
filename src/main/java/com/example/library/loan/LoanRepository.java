package com.example.library.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByReaderId(Long readerId);

    List<Loan> findByReturnDateIsNullAndDueDateBefore(LocalDate date);

    boolean existsByReaderId(Long id);

    boolean existsByBookId(Long id);
}
