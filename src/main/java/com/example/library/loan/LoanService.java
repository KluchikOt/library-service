package com.example.library.loan;

import com.example.library.book.Book;
import com.example.library.book.BookRepository;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.loan.dto.LoanCreateRequest;
import com.example.library.loan.dto.LoanResponse;
import com.example.library.reader.Reader;
import com.example.library.reader.ReaderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    public LoanService(LoanRepository loanRepository, LoanMapper loanMapper, BookRepository bookRepository, ReaderRepository readerRepository) {
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    @Transactional
    public LoanResponse returnBook(Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Loan с ID: " + id + " не найден."));
        loan.loanReturn();
        loan.getBook().bookReturned();
        return loanMapper.toResponse(loan);
    }

    @Transactional
    public LoanResponse loanGiven(LoanCreateRequest request) {
        Book book = bookRepository.findById(request.bookId()).orElseThrow(() -> new ResourceNotFoundException("Книга с ID: " + request.bookId() + " не найдена."));
        Reader reader = readerRepository.findById(request.readerId()).orElseThrow(() -> new ResourceNotFoundException("Читатель с ID: " + request.readerId() + " не найден."));
        book.bookGiven();
        Loan loan = new Loan(book, reader, request.dueDate());
        loanRepository.save(loan);
        return loanMapper.toResponse(loan);
    }

    @Transactional(readOnly = true)
    public List<LoanResponse> getLoansByReader(Long readerId) {
        if (!readerRepository.existsById(readerId)) {
            throw new ResourceNotFoundException("Читатель с ID: " + readerId + " не найден.");
        }

        List<Loan> readerLoans = loanRepository.findByReaderId(readerId);
        List<LoanResponse> response = new ArrayList<>();

        for (Loan loan : readerLoans) {
            response.add(loanMapper.toResponse(loan));
        }

        return response;
    }

    @Transactional(readOnly = true)
    public List<LoanResponse> getOverdueLoans() {
        LocalDate today = LocalDate.now();

        List<Loan> overdueLoans = loanRepository.findByReturnDateIsNullAndDueDateBefore(today);
        List<LoanResponse> response = new ArrayList<>();

        for (Loan loan : overdueLoans) {
            response.add(loanMapper.toResponse(loan));
        }

        return response;
    }
}
