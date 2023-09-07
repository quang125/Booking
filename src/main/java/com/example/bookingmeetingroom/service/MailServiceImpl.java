package com.example.bookingmeetingroom.service;

import com.example.bookingmeetingroom.utils.JwtTokenUtil;
import com.example.bookingmeetingroom.dao.EmailDAO;
import com.example.bookingmeetingroom.model.entity.Email;
import com.example.bookingmeetingroom.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class MailServiceImpl implements MailService{
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private AuthService authService;
    @Autowired
    private EmailDAO emailDAO;

    @Override
    public void sendResetPasswordToken() {
        User currentUser=authService.getCurrentLoggedInUser();
        String token=JwtTokenUtil.generateToken(currentUser.getUsername(),60*60*60);
        String resetPasswordLink = "http://localhost:8080/api/auth/reset-password";
        String emailBody = "Mã token reset password: " + token + "\n\n"
                + "Để đặt lại mật khẩu vui lòng nhấn vào đường link sau:\n"
                + resetPasswordLink;
        sendMail(currentUser.getEmail(),"Dưới đây là token reset password của bạn", emailBody);
        Email email=new Email(new Date(),"Dưới đây là token reset password của bạn",currentUser.getEmail(),"reset_password",currentUser);
        emailDAO.save(email);
    }

    @Override
    public void sendPassword() {
        User currentUser=authService.getCurrentLoggedInUser();
        sendMail(currentUser.getEmail(), "Dưới đây là mật khẩu của bạn:", currentUser.getPassword());
        Email email=new Email(new Date(),"Dưới đây là mật khẩu của bạn",currentUser.getEmail(),"show_password",currentUser);
        emailDAO.save(email);
    }
    @Override
    public void scheduleEmailSending(String mail, Date expireDate) {
        Timer timer = new Timer();
        long currentTimeMillis = System.currentTimeMillis();
        long expireTimeMillis = expireDate.getTime();
        long delayMillis = expireTimeMillis - currentTimeMillis - 15 * 60 * 1000-7*60*60*1000;
        System.out.println(expireDate+" "+ LocalDateTime.now());
        System.out.println(delayMillis);
        User currentUser=authService.getCurrentLoggedInUser();
        Email email=new Email(new Date(),"Nhắc lịch họp:",currentUser.getEmail(),"reminder_meeting",currentUser);
        if (delayMillis > 0) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    emailDAO.save(email);
                    sendMail(mail, "Nhắc lịch họp:", "Còn 15 phút nữa là đến cuộc họp");
                    timer.cancel();
                }
            }, delayMillis);
        }
    }
    public void sendMail(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
