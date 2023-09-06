package com.example.bookingmeetingroom.model.dto;

import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginFormDTO {
    @NotNull()
    @Size(min=6,max=20, message = "Username must contains from 6 to 20 characters")
    @NotBlank(message = "Username must not contains space character")
    private String username;
    @NotNull()
    @Size(min=6,max=20, message = "Password must contains from 6 to 20 characters")
    @NotBlank(message = "Password must not contains space character")
    private String password;
}
