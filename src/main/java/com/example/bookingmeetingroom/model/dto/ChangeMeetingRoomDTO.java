package com.example.bookingmeetingroom.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeMeetingRoomDTO {
    private Long meetingId;
    @NotNull
    private String newRoomName;
}
