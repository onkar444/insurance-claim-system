package com.backend.controller;

import com.backend.model.User;
import com.backend.model.dto.UserRequestDTO;
import com.backend.model.dto.UserResponseDTO;
import com.backend.service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> getUserId(@PathVariable Long id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/user/save")
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(userService.saveUser(userRequestDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/user/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO user, @PathVariable Long id){
        return ResponseEntity.ok(userService.updateUser(user,id));
    }

    //    Delete/deactivate user
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.deleteById(id));
    }

}
