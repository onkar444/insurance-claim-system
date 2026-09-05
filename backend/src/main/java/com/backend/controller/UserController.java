package com.backend.controller;

import com.backend.model.User;
import com.backend.model.dto.UserRequestDTO;
import com.backend.model.dto.UserResponseDTO;
import com.backend.service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> getUserId(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping("/user/save")
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(userService.saveUser(userRequestDTO));
    }

    @PostMapping("/user/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO user, @PathVariable Integer id){
        return ResponseEntity.ok(userService.updateUser(user,id));
    }

    //    Delete/deactivate user
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.deleteById(id));
    }
}
