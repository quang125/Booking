package com.example.bookingmeetingroom.controller;

import com.example.bookingmeetingroom.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
public class MailController {
    @Autowired
    private MailService mailService;
    @GetMapping("/reset-password")
    public ResponseEntity<String>sendResetPasswordToken(){
        mailService.sendResetPasswordToken();
        return ResponseEntity.ok("Mã đặt lại mật khẩu đã được gửi về mail của bạn");
    }
    @GetMapping("/send-password")
    public ResponseEntity<String>sendPassword(){
        mailService.sendPassword();
        return ResponseEntity.ok("Mật khẩu đã được gửi về mail của bạn");
    }

}
