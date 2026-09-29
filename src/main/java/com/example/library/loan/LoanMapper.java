package com.example.library.loan;



import com.example.library.loan.dto.LoanResponse;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {



    public LoanResponse toResponse(Loan loan) {

        return new LoanResponse(loan.getId(), loan.getBook().getId(), loan.getReader().getId(), loan.getLoanDate(), loan.getDueDate(), loan.getReturnDate(), loan.getStatus());
    }
}


