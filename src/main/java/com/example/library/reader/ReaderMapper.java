package com.example.library.reader;


import com.example.library.reader.dto.ReaderCreateRequest;
import com.example.library.reader.dto.ReaderResponse;
import org.springframework.stereotype.Component;



@Component
public class ReaderMapper {
    public Reader toEntity(ReaderCreateRequest request) {

        return new Reader(request.firstName(), request.lastName(), request.email(), request.phoneNumber());

    }


    public ReaderResponse toResponse(Reader reader) {

        return new ReaderResponse(reader.getId(), reader.getFirstName(), reader.getLastName(), reader.getEmail(), reader.getPhoneNumber(), reader.getRegistrationDate());
    }
}

