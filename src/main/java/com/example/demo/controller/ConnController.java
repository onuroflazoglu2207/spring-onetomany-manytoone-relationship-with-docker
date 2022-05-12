package com.example.demo.controller;


import com.example.demo.dto.ConnRequestDTO;
import com.example.demo.service.ConnServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/conns")
@RequiredArgsConstructor
public class ConnController {

    private final ConnServiceImpl service;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return service.getAll();
    }

    @GetMapping("/getById/{identity}")
    public ResponseEntity<?> getById(@PathVariable Long identity) {
        return service.getById(identity);
    }

    @PutMapping("/update/{identity}")
    public ResponseEntity<?> update(@RequestBody ConnRequestDTO dto, @PathVariable Long identity) {
        return service.update(dto, identity);
    }
}
