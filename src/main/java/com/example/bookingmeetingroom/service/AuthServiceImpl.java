package com.example.bookingmeetingroom.service;

import com.example.bookingmeetingroom.utils.JwtTokenUtil;
import com.example.bookingmeetingroom.dao.UserDAO;
import com.example.bookingmeetingroom.exception.UserAlreadyExistException;
import com.example.bookingmeetingroom.model.CustomUserDetails;
import com.example.bookingmeetingroom.model.dto.ChangePasswordFormDTO;
import com.example.bookingmeetingroom.model.dto.GoogleLoginFormDTO;
import com.example.bookingmeetingroom.model.dto.LoginFormDTO;
import com.example.bookingmeetingroom.model.dto.RegistrationFormDTO;
import com.example.bookingmeetingroom.model.entity.User;
import com.example.bookingmeetingroom.security.jwt.JwtResponse;
import com.example.bookingmeetingroom.security.jwt.JwtTokenProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public ResponseEntity<?> login(LoginFormDTO loginFormDTO) {
        try{
            return authenticateUser(loginFormDTO.getUsername(), loginFormDTO.getPassword());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sai email hoặc mật khẩu!");
        }
    }

    @NotNull
    private ResponseEntity<?> authenticateUser(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        User currentUser=userDAO.findByUsername(username).get();
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getRole(), currentUser.getUsername(), currentUser.getName()));
    }

    @Override
    public ResponseEntity<?> register(RegistrationFormDTO registrationFormDTO) {
        try {
            if (!userDAO.findByUsername(registrationFormDTO.getUsername()).isPresent() && !userDAO.findByEmail(registrationFormDTO.getEmail()).isPresent()) {
                User user = new User();
                user.setName(registrationFormDTO.getName());
                user.setPassword(passwordEncoder.encode(registrationFormDTO.getPassword()));
                user.setEmail(registrationFormDTO.getEmail());
                user.setUsername(registrationFormDTO.getUsername());
                user.setRole("ROLE_USER");
                userDAO.save(user);
                return authenticateUser(registrationFormDTO.getUsername(), registrationFormDTO.getPassword());
            }
            throw new UserAlreadyExistException("Đã tồn tại người dùng, vui lòng chọn tên đăng nhập hoặc email khác");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Override
    public User getCurrentLoggedInUser() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User user=userDAO.findByUsername(username).get();
        return user;
    }

    @Override
    public ResponseEntity<?> changePassword(ChangePasswordFormDTO changePasswordFormDTO) {
        String token=changePasswordFormDTO.getToken();
        if(!JwtTokenUtil.validateToken(token)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token không hợp lệ");
        User user=getCurrentLoggedInUser();
        if(JwtTokenUtil.getExpireDate(token).before(new Date())) System.out.println(JwtTokenUtil.getExpireDate(token));
        if(!user.getUsername().equals(JwtTokenUtil.getSubject(token))||JwtTokenUtil.getExpireDate(token).before(new Date()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Token này đã hết hạn hoặc không thuộc quyền của bạn");
        user.setPassword(passwordEncoder.encode(changePasswordFormDTO.getNewPassword()));
        userDAO.save(user);
        return ResponseEntity.ok("Mật khẩu được đặt lại thành công");
    }

    @Override
    public void loginWithGoogle(GoogleLoginFormDTO googleLoginFormDTO) {

    }
}
