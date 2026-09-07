package com.backend.utility;

import com.backend.model.User;
import com.backend.model.dto.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserResponseDTO entityToDTO(User savedUser) {
        return new UserResponseDTO(savedUser.getId(), savedUser.getName(), savedUser.getRole(), savedUser.getEmail());
    }
}
