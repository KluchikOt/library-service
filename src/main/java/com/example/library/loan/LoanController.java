package com.example.library.loan;


import com.example.library.loan.dto.LoanCreateRequest;
import com.example.library.loan.dto.LoanResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/reader/{readerId}")
    public ResponseEntity<List<LoanResponse>> getLoansByReader(@PathVariable Long readerId) {
        List<LoanResponse> response = loanService.getLoansByReader(readerId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<LoanResponse>> getOverdueLoans() {
        List<LoanResponse> response = loanService.getOverdueLoans();
        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<LoanResponse> loanGiven(@Valid @RequestBody LoanCreateRequest request) {
        LoanResponse response = loanService.loanGiven(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<LoanResponse> loanReturned(@PathVariable Long id) {
        LoanResponse updatedLoan = loanService.returnBook(id);
        return ResponseEntity.ok(updatedLoan);
    }



}
