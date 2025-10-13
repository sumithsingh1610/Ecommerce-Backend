package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.UserRequestDto;
import com.ecommerce.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRequestDto userDto) {
        String response = userService.registerUser(userDto);
        return ResponseEntity.ok(response);
    }
}
