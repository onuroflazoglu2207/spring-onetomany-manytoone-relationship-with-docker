package com.example.demo.mapper;

import com.example.demo.decorator.UserMapperDecorator;
import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.model.UserModel;
import org.mapstruct.*;

import java.util.List;

@Mapper
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {

    UserRequestDTO modelUserRequestDto(UserModel userModel);

    List<UserRequestDTO> modelUserRequestDtoList(List<UserModel> userModelList);

    UserModel requestUserModel(UserRequestDTO userRequestDTO);

    List<UserModel> requestUserModelList(List<UserRequestDTO> userRequestDTOList);

    UserResponseDTO modelUserResponseDto(UserModel userModel);

    List<UserResponseDTO> modelUserResponseDtoList(List<UserModel> userModelList);

    UserModel responseUserModel(UserResponseDTO userResponseDTO);

    List<UserModel> responseUserModelList(List<UserResponseDTO> userResponseDTOList);

    void modelRequestUserModel(@MappingTarget UserModel userModel, UserRequestDTO userRequestDTO);
}
