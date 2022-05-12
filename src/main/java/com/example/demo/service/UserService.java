package com.example.demo.service;

import com.example.demo.dto.UserRequestDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public ResponseEntity<?> getAll();

    public ResponseEntity<?> getById(Long identity);

    public ResponseEntity<?> save(UserRequestDTO dto);

    public ResponseEntity<?> update(UserRequestDTO dto, Long identity);

    public ResponseEntity<?> delete(Long identity);
}
