package com.example.bookingmeetingroom.controller;

import com.example.bookingmeetingroom.model.dto.ChangePasswordFormDTO;
import com.example.bookingmeetingroom.model.dto.GoogleLoginFormDTO;
import com.example.bookingmeetingroom.model.dto.LoginFormDTO;
import com.example.bookingmeetingroom.model.dto.RegistrationFormDTO;
import com.example.bookingmeetingroom.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginFormDTO loginFormDTO){
        return authService.login(loginFormDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationFormDTO registrationFormDTO){
        return authService.register(registrationFormDTO);
    }
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ChangePasswordFormDTO changePasswordFormDTO){
        return authService.changePassword(changePasswordFormDTO);
    }
}
