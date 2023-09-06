package com.example.bookingmeetingroom.security.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String role;
    private String accessToken;
    private String tokenType = "Bearer";
    private String username;
    private String name;


    public JwtResponse(String accessToken, String role, String username, String name) {
        this.accessToken = accessToken;
        this.username = username;
        this.name = name;
        this.role = role;
    }


}