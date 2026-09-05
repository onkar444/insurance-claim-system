package com.backend.service;

import com.backend.model.User;
import com.backend.model.dto.UserRequestDTO;
import com.backend.model.dto.UserResponseDTO;
import com.backend.repository.UserRepository;
import com.backend.repository.exception.UserNotFoundException;
import com.backend.utility.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(MapperUtils::mapUserEntityToResponseDTO)
                .toList();
    }

    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        var user = userRepository.save(MapperUtils.mapUserRequestDTOtoEntity(userRequestDTO));
        return MapperUtils.mapUserEntityToResponseDTO(user);
    }

    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO, Integer id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with Id::" + id));

        existingUser.setRole(userRequestDTO.role());
        existingUser.setPassword(userRequestDTO.password());
        existingUser.setName(userRequestDTO.name());
        existingUser.setEmail(userRequestDTO.email());

        userRepository.save(existingUser);

        return MapperUtils.mapUserEntityToResponseDTO(existingUser);
    }

    public String deleteById(Integer id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

    public UserResponseDTO getById(Integer id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with Id::" + id));

        return MapperUtils.mapUserEntityToResponseDTO(existingUser);
    }
}
