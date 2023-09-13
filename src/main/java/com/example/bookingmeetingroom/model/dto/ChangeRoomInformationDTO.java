package com.example.bookingmeetingroom.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRoomInformationDTO {
    @NotNull
    private int capacity;
    private String newName;
    private String oldName;
}
