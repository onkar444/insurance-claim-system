package com.backend.controller;

import com.backend.model.User;
import com.backend.service.UserService;
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
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PostMapping("/user/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Integer id){
        return ResponseEntity.ok(userService.updateUser(user,id));
    }

    //    Delete/deactivate user
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.deleteById(id));
    }
}
