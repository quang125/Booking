package com.example.bookingmeetingroom.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Email")
public class Email {
    @Id
    private String id;
    @Past
    private Date timeSend;
    @NotNull
    private String subject;
    @javax.validation.constraints.Email
    private String mailReceive;

    public Email(Date timeSend, String subject, String mailReceive, String type,  User user) {
        this.timeSend = timeSend;
        this.subject = subject;
        this.mailReceive = mailReceive;
        this.type = type;
        this.user = user;
    }

    @NotNull
    private String type;
    private User user;
}
