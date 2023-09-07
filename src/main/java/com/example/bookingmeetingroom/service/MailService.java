package com.example.bookingmeetingroom.service;

import java.util.Date;

public interface MailService {
    public void sendResetPasswordToken();
    public void sendPassword();
    public void scheduleEmailSending(String email, Date expireDate);
}
