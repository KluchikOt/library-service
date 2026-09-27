package com.example.library.book;

import com.example.library.book.dto.BookCreateRequest;
import com.example.library.book.dto.BookResponse;
import com.example.library.exception.ResourceAlreadyExistException;
import com.example.library.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Transactional
    public BookResponse createBook(BookCreateRequest request) {
        if(bookRepository.existsByIsbn(request.isbn())) {throw new ResourceAlreadyExistException("Данная книга уже внесена");}
        Book book = bookRepository.save(bookMapper.toEntity(request));
        return bookMapper.toResponse(book);

    }

    @Transactional(readOnly = true)
    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Неверный ID элемента:" + id));
        return bookMapper.toResponse(book);
    }


}
