package com.example.demo.service;

import com.example.demo.dto.ConnRequestDTO;
import com.example.demo.mapper.ConnMapper;
import com.example.demo.model.ConnModel;
import com.example.demo.model.UserModel;
import com.example.demo.repository.ConnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConnServiceImpl implements ConnService {

    private final ConnRepository connRepository;
    private final ConnMapper connMapper;


    @Override
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(connMapper.modelConnResponseDtoList(connRepository.findAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(Long identity) {
        Optional<ConnModel> optional = connRepository.findById(identity);
        ConnModel model = optional.isEmpty() ? null : optional.get();
        if (model != null)
            return new ResponseEntity<>(connMapper.modelConnResponseDto(model), HttpStatus.OK);
        else
            return new ResponseEntity<>("Conn is not found!", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> update(ConnRequestDTO dto, Long identity) {
        try {
            Optional<ConnModel> optional = connRepository.findById(identity);
            ConnModel model = optional.isEmpty() ? null : optional.get();
            if (model == null)
                return new ResponseEntity<>("Conn is not found!", HttpStatus.NOT_FOUND);
            connMapper.modelRequestConnModel(model, dto);
            connRepository.save(model);
            return new ResponseEntity<>(connMapper.modelConnResponseDto(model), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Have unacceptable field!", HttpStatus.CONFLICT);
        }
    }
}
