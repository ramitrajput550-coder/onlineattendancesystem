package com.attendance.controller;

import com.attendance.dto.AuthDto;
import com.attendance.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthDto.LoginResponse> login(@RequestBody AuthDto.LoginRequest request) {
        AuthDto.LoginResponse response = authService.authenticate(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthDto.LoginResponse> register(@RequestBody AuthDto.RegisterRequest request) {
        AuthDto.LoginResponse response = authService.registerStudent(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(400).body(response);
        }
    }

    @PostMapping("/register/teacher")
    public ResponseEntity<AuthDto.LoginResponse> registerTeacher(@RequestBody AuthDto.TeacherRegisterRequest request) {
        AuthDto.LoginResponse response = authService.registerTeacher(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(400).body(response);
        }
    }
}
