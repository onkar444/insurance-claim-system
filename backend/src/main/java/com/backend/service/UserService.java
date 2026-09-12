package com.backend.service;

import com.backend.model.User;
import com.backend.model.dto.UserRequestDTO;
import com.backend.model.dto.UserResponseDTO;
import com.backend.repository.UserRepository;
import com.backend.exception.UserNotFoundException;
import com.backend.security.JwtUtil;
import com.backend.utility.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public List<UserResponseDTO> getAllUsers() {
        LOGGER.info("Executing getAllUsers()");
        return userRepository.findAll()
                .stream()
                .filter(user-> !user.isDeleted())
                .map(UserMapper::entityToResponseDTO)
                .toList();
    }

    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        LOGGER.info("Executing saveUser()");
        var user = userRepository.save(UserMapper.requestDTOtoEntity(userRequestDTO));
        return UserMapper.entityToResponseDTO(user);
    }

    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO, Long id) {
        LOGGER.info("Executing updateUser()");

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with Id::" + id));

        existingUser.setRoles(userRequestDTO.roles());
        existingUser.setPassword(userRequestDTO.password());
        existingUser.setName(userRequestDTO.name());
        existingUser.setEmail(userRequestDTO.email());

        userRepository.save(existingUser);

        return UserMapper.entityToResponseDTO(existingUser);
    }

    public String deleteById(Long id) {
        LOGGER.info("Executing deleteById()");

        var user = userRepository.findById(id)
                        .orElseThrow(()-> new UserNotFoundException("User not found with Id::"+id));
        
        user.setDeleted(Boolean.TRUE);
        userRepository.save(user);
        return "User deleted successfully";
    }

    public UserResponseDTO getById(Long id) {
        LOGGER.info("Executing getById()");
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with Id::" + id));

        return UserMapper.entityToResponseDTO(existingUser);
    }

    public void registerUser(User user) {
        LOGGER.info("Executing registerUser()");

        var savedUser = userRepository.save(user);
        UserMapper.entityToResponseDTO(savedUser);
    }

    public UserResponseDTO getUserMe(Authentication authentication) {
        User userEntity =  userRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new UserNotFoundException("No user found with username::"+ authentication.getName()));

        return UserMapper.entityToResponseDTO(userEntity);
    }
}
