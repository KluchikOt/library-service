package com.example.library.book;

import com.example.library.book.dto.BookCreateRequest;
import com.example.library.book.dto.BookResponse;
import com.example.library.book.dto.BookUpdateRequest;
import com.example.library.exception.ResourceAlreadyExistException;
import com.example.library.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Книга с ID: " + id + " не найдена."));
        return bookMapper.toResponse(book);
    }

    @Transactional(readOnly = true)
    public Page<BookResponse> getBooks(Pageable pageable) {
        Page<Book> booksPage = bookRepository.findAll(pageable);
        return booksPage.map(bookMapper::toResponse);
    }

    @Transactional
    public BookResponse updateBook(Long id, BookUpdateRequest updateRequest) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Книга с ID: " + id + " не найдена."));
        book.updateData( updateRequest.title(), updateRequest.author(), updateRequest.publicationYear(), updateRequest.totalCopies());
        return bookMapper.toResponse(book);
    }

    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Книга с ID: " + id + " не найдена."));
        bookRepository.delete(book);
    }

}
