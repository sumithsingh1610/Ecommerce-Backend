package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.UserLoginDto;
import com.ecommerce.backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDto loginDto) {
        String jwtToken = authService.loginUser(loginDto);
        return ResponseEntity.ok(jwtToken);
    }

}
