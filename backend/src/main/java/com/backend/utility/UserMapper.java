package com.backend.utility;

import com.backend.model.User;
import com.backend.model.dto.UserRequestDTO;
import com.backend.model.dto.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserResponseDTO entityToResponseDTO(User savedUser) {
        return new UserResponseDTO(savedUser.getId(), savedUser.getName(), savedUser.getRoles(), savedUser.getEmail());
    }


    public static User requestDTOtoEntity(UserRequestDTO userRequestDTO) {
        return User.builder()
                .email(userRequestDTO.email())
                .roles(userRequestDTO.roles())
                .name(userRequestDTO.name())
                .build();
    }
}
