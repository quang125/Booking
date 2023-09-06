package com.example.bookingmeetingroom.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordFormDTO {
    @NotNull
    @NotBlank
    @Size(min = 6, max = 20, message = "Password chứa tối thiểu 6 kí tự và tối đa 20 kí tự")
    private String newPassword;
    @NotNull
    @NotBlank
    private String token;
}
