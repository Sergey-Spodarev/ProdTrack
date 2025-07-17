package com.example.ProdTrack.controller;

import com.example.ProdTrack.DTO.UserDTO;
import com.example.ProdTrack.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register/user")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        userDTO.setRole("USER");
        UserDTO saveUser = userService.save(userDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saveUser);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<UserDTO> registerAdmin(@RequestBody UserDTO userDTO) {
        userDTO.setRole("ADMIN");
        UserDTO saveUser = userService.save(userDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saveUser);
    }
}
