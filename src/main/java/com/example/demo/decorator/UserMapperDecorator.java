package com.example.demo.decorator;

import com.example.demo.dto.ConnResponseDTO;
import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.ConnModel;
import com.example.demo.model.UserModel;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserMapperDecorator implements UserMapper {

    @Setter(onMethod = @__({@Autowired}))
    private UserMapper mapper;


    @Override
    public UserRequestDTO modelUserRequestDto(UserModel userModel) {
        return mapper.modelUserRequestDto(userModel);
    }

    @Override
    public List<UserRequestDTO> modelUserRequestDtoList(List<UserModel> userModelList) {
        return mapper.modelUserRequestDtoList(userModelList);
    }

    @Override
    public UserModel requestUserModel(UserRequestDTO userRequestDTO) {
        return setUser(mapper.requestUserModel(userRequestDTO));
    }

    @Override
    public List<UserModel> requestUserModelList(List<UserRequestDTO> userRequestDTOList) {
        return listToList(userRequestDTOList, (e) -> {
            return requestUserModel((UserRequestDTO) e);
        });
    }

    @Override
    public UserResponseDTO modelUserResponseDto(UserModel userModel) {
        UserResponseDTO userResponseDTO = mapper.modelUserResponseDto(userModel);
        for (ConnResponseDTO conn : userResponseDTO.getConns())
            conn.setParent(userModel.getIdentity());
        return userResponseDTO;
    }


    @Override
    public List<UserResponseDTO> modelUserResponseDtoList(List<UserModel> userModelList) {
        return listToList(userModelList, (e) -> {
            return modelUserResponseDto((UserModel) e);
        });
    }

    @Override
    public UserModel responseUserModel(UserResponseDTO userResponseDTO) {
        return setUser(mapper.responseUserModel(userResponseDTO));
    }

    @Override
    public List<UserModel> responseUserModelList(List<UserResponseDTO> userResponseDTOList) {
        return listToList(userResponseDTOList, (e) -> {
            return responseUserModel((UserResponseDTO) e);
        });
    }

    @Override
    public void modelRequestUserModel(UserModel userModel, UserRequestDTO userRequestDTO) {
        mapper.modelRequestUserModel(userModel, userRequestDTO);
        setUser(userModel);
    }

    private UserModel setUser(UserModel model) {
        for (ConnModel conn : model.getConns())
            conn.setUser(model);
        return model;
    }

    private <T, C> List<T> listToList(List<C> list, ListInter listInter) {
        List<T> temp = new ArrayList<>();
        for (C dto : list)
            temp.add((T) listInter.action(dto));
        return temp;
    }

    private interface ListInter<T, C> {
        public T action(C c);
    }
}
