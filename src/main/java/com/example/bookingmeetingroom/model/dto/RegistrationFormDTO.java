package com.example.bookingmeetingroom.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationFormDTO {
    @NotNull
    @Size(min = 6,max = 20)
    @NotBlank
    public String username;
    @NotNull
    @Size(min = 2,max = 50)
    public String name;
    @NotNull
    @Size(min = 6,max = 20)
    @NotBlank
    public String password;
    @Email
    public String email;
}
