package com.ozgurokanozdal.habitTracker.controller;

import com.ozgurokanozdal.habitTracker.dto.AuthRequest;
import com.ozgurokanozdal.habitTracker.dto.AuthResponse;
import com.ozgurokanozdal.habitTracker.dto.UserCreateRequest;
import com.ozgurokanozdal.habitTracker.dto.UserResponse;
import com.ozgurokanozdal.habitTracker.service.AuthService;
import com.ozgurokanozdal.habitTracker.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    public AuthController(JwtService jwtService,AuthenticationManager authenticationManager,AuthService authService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Hoşgeldin cano! This is Ozgur Okan Ozdal project";
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserCreateRequest userCreateRequest){
        return ResponseEntity.ok(authService.register(userCreateRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
