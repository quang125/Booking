package com.example.bookingmeetingroom.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomFormDTO {
    @NotNull
    private int capacity;
    @Column(unique = true)
    private String roomName;
}
