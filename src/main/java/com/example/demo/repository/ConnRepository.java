package com.example.demo.repository;

import com.example.demo.model.ConnModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnRepository extends JpaRepository<ConnModel, Long> {
}