package com.backend.service;

import com.backend.model.Claim;
import com.backend.model.User;
import com.backend.model.dto.UserRequestDTO;
import com.backend.model.dto.UserResponseDTO;
import com.backend.repository.ClaimRepositroy;
import com.backend.repository.UserRepository;
import com.backend.repository.exception.ClaimNotFoundException;
import com.backend.repository.exception.UserNotFoundException;
import com.backend.utility.PolicyMapper;
import com.backend.utility.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private ClaimRepositroy claimRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .filter(user-> !user.isDeleted())
                .map(UserMapper::entityToResponseDTO)
                .toList();
    }

    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        var user = userRepository.save(UserMapper.requestDTOtoEntity(userRequestDTO));
        return UserMapper.entityToResponseDTO(user);
    }

    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO, Long id) {
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
        var user = userRepository.findById(id)
                        .orElseThrow(()-> new UserNotFoundException("User not found with Id::"+id));
        
        user.setDeleted(Boolean.TRUE);
        userRepository.save(user);
        return "User deleted successfully";
    }

    public UserResponseDTO getById(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with Id::" + id));

        return UserMapper.entityToResponseDTO(existingUser);
    }

    public void registerUser(User user) {
        var savedUser = userRepository.save(user);
        UserMapper.entityToResponseDTO(savedUser);
    }
}
