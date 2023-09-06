package com.example.bookingmeetingroom.service;

import com.example.bookingmeetingroom.model.dto.ChangePasswordFormDTO;
import com.example.bookingmeetingroom.model.dto.GoogleLoginFormDTO;
import com.example.bookingmeetingroom.model.dto.LoginFormDTO;
import com.example.bookingmeetingroom.model.dto.RegistrationFormDTO;
import com.example.bookingmeetingroom.model.entity.User;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public ResponseEntity<?> login(LoginFormDTO loginFormDTO);
    public ResponseEntity<?> register(RegistrationFormDTO registrationFormDTO);
    public User getCurrentLoggedInUser();
    public ResponseEntity<?> changePassword(ChangePasswordFormDTO changePasswordFormDTO);
    public void loginWithGoogle(GoogleLoginFormDTO googleLoginFormDTO);
}
