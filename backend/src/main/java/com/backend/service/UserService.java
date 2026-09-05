package com.backend.service;

import com.backend.model.User;
import com.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user, Integer id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with Id::" + id));

        existingUser.setClaims(user.getClaims());
        existingUser.setEmail(user.getEmail());
        existingUser.setName(user.getName());
        existingUser.setPassword(user.getPassword());
        existingUser.setPolicies(user.getPolicies());
        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);
    }

    public String deleteById(Integer id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

    public User getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Policy not found with id:"+id));
    }
}
