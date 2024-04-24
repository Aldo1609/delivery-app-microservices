package com.aldob.login.mapper;

import com.aldob.login.dto.UserDTO;
import com.aldob.login.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(UserEntity userEntity);

    UserEntity toEntity(UserDTO userDTO);

    List<UserDTO> toDTO(List<UserEntity> userEntities);

}
