package com.example.library.reader;


import com.example.library.exception.ResourceNotFoundException;
import com.example.library.reader.dto.ReaderCreateRequest;
import com.example.library.reader.dto.ReaderResponse;
import com.example.library.reader.dto.ReaderUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final ReaderMapper readerMapper;

    public ReaderService(ReaderRepository readerRepository, ReaderMapper readerMapper) {
        this.readerRepository = readerRepository;
        this.readerMapper = readerMapper;
    }

    @Transactional
    public ReaderResponse createReader(ReaderCreateRequest request) {
        Reader reader = readerRepository.save(readerMapper.toEntity(request));
        return readerMapper.toResponse(reader);

    }

    @Transactional(readOnly = true)
    public ReaderResponse getReaderById(Long id) {
        Reader reader = readerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Читатель с ID: " + id + " не найден."));
        return readerMapper.toResponse(reader);
    }


    @Transactional
    public ReaderResponse updateReader(Long id, ReaderUpdateRequest updateRequest) {
        Reader reader = readerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Читатель с ID: " + id + " не найден."));
        reader.updateData(updateRequest.lastName(), updateRequest.email(), updateRequest.phoneNumber());
        return readerMapper.toResponse(reader);
    }

    @Transactional
    public void deleteReader(Long id) {
        Reader reader = readerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Читатель с ID: " + id + " не найден."));
        readerRepository.delete(reader);
    }

    @Transactional(readOnly = true)
    public Page<ReaderResponse> getReaders(Pageable pageable) {
        Page<Reader> readerPage = readerRepository.findAll(pageable);
        return readerPage.map(readerMapper::toResponse);
    }

}
