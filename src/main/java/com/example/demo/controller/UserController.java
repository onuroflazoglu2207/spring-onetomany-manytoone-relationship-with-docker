package com.example.demo.controller;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl service;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return service.getAll();
    }

    @GetMapping("/getById/{identity}")
    public ResponseEntity<?> getById(@PathVariable Long identity) {
        return service.getById(identity);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody UserRequestDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/update/{identity}")
    public ResponseEntity<?> update(@RequestBody UserRequestDTO dto, @PathVariable Long identity) {
        return service.update(dto, identity);
    }

    @DeleteMapping("/delete/{identity}")
    public ResponseEntity<?> delete(@PathVariable Long identity) {
        return service.delete(identity);
    }
}