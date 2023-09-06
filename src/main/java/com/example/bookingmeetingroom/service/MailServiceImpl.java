package com.example.bookingmeetingroom.service;

import com.example.bookingmeetingroom.Utils.JwtTokenUtil;
import com.example.bookingmeetingroom.dao.UserDAO;
import com.example.bookingmeetingroom.model.entity.User;
import com.example.bookingmeetingroom.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MailServiceImpl implements MailService{
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private AuthService authService;

    @Override
    public void sendResetPasswordToken() {
        User currentUser=authService.getCurrentLoggedInUser();
        String token=JwtTokenUtil.generateToken(currentUser.getUsername(),60*60*60);
        String resetPasswordLink = "http://localhost:8080/api/auth/reset-password";
        String emailBody = "Mã token reset password: " + token + "\n\n"
                + "Để đặt lại mật khẩu vui lòng nhấn vào đường link sau:\n"
                + resetPasswordLink;
        sendMail(currentUser.getEmail(),"Dưới đây là token reset password của bạn", emailBody);
    }

    @Override
    public void sendPassword() {
        User currentUser=authService.getCurrentLoggedInUser();
        sendMail(currentUser.getEmail(), "Dưới đây là mật khẩu của bạn:", currentUser.getPassword());
    }
    public void sendMail(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
