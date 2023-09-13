package com.example.bookingmeetingroom.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "User")
public class User {
    @Id
    private String id;

    private String username;

    private String password;

    private String name;
    private String email;

    private String role;

    public User(String username, String password, String name, String email, String role){
        this.username=username;
        this.password=password;
        this.name=name;
        this.email=email;
        this.role=role;
    }
}
