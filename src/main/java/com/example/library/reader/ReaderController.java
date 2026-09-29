package com.example.library.reader;


import com.example.library.reader.dto.ReaderCreateRequest;
import com.example.library.reader.dto.ReaderResponse;
import com.example.library.reader.dto.ReaderUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {

    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReaderResponse> getReader(@PathVariable Long id) {
        ReaderResponse response = readerService.getReaderById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ReaderResponse>> getReaders(Pageable pageable) {
        Page<ReaderResponse> readers = readerService.getReaders(pageable);
        return ResponseEntity.ok(readers);
    }

    @PostMapping
    public ResponseEntity<ReaderResponse> createReader(@Valid @RequestBody ReaderCreateRequest request) {
        ReaderResponse response = readerService.createReader(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReaderResponse> updateReader(@PathVariable Long id, @Valid @RequestBody ReaderUpdateRequest updateRequest) {
        ReaderResponse updatedReader = readerService.updateReader(id, updateRequest);
        return ResponseEntity.ok(updatedReader);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable Long id) {
        readerService.deleteReader(id);
        return ResponseEntity.noContent().build();
    }

}
