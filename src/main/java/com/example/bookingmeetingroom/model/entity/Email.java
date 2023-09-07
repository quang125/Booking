package com.example.bookingmeetingroom.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
}
