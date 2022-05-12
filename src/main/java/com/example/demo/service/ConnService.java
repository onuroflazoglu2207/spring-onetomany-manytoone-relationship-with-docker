package com.example.demo.service;

import com.example.demo.dto.ConnRequestDTO;
import org.springframework.http.ResponseEntity;

public interface ConnService {
    public ResponseEntity<?> getAll();

    public ResponseEntity<?> getById(Long identity);

    public ResponseEntity<?> update(ConnRequestDTO dto, Long identity);
}
