package com.example.bookingmeetingroom.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRoomInformationDTO {
    @NotNull
    private int capacity;
    @Column(unique = true)
    private String newName;
    private String oldName;
}
