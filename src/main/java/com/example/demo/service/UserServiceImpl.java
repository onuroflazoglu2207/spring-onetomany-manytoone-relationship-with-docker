package com.example.demo.service;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(userMapper.modelUserResponseDtoList(userRepository.findAll()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(Long identity) {
        Optional<UserModel> optional = userRepository.findById(identity);
        UserModel model = optional.isEmpty() ? null : optional.get();
        if (model != null)
            return new ResponseEntity<>(userMapper.modelUserResponseDto(model), HttpStatus.OK);
        else
            return new ResponseEntity<>("User is not found!", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> save(UserRequestDTO dto) {
        try {
            UserModel model = userMapper.requestUserModel(dto);
            model = userRepository.save(model);
            return new ResponseEntity<>(userMapper.modelUserResponseDto(model), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Have unacceptable field!", HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<?> update(UserRequestDTO dto, Long identity) {
        try {
            Optional<UserModel> optional = userRepository.findById(identity);
            UserModel model = optional.isEmpty() ? null : optional.get();
            if (model == null)
                return new ResponseEntity<>("User is not found!", HttpStatus.NOT_FOUND);
            userMapper.modelRequestUserModel(model, dto);
            userRepository.save(model);
            return new ResponseEntity<>(userMapper.modelUserResponseDto(model), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Have unacceptable field!", HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<?> delete(Long identity) {
        Optional<UserModel> optional = userRepository.findById(identity);
        UserModel model = optional.isEmpty() ? null : optional.get();
        if (model != null) {
            userRepository.deleteById(identity);
            return new ResponseEntity<>(userMapper.modelUserResponseDto(model), HttpStatus.OK);
        } else return new ResponseEntity<>("User is not found!", HttpStatus.NOT_FOUND);
    }
}