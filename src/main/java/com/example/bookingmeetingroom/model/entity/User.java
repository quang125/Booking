package com.example.bookingmeetingroom.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;

    private String role;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Meeting> meetingList;

    public User(String username, String password, String name, String email, String role){
        this.username=username;
        this.password=password;
        this.name=name;
        this.email=email;
        this.role=role;
    }
}