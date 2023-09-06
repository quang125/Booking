package com.example.bookingmeetingroom.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingDTO {
    private Date startTime;
    private Date endTime;
    private String roomName;
    private String bookerName;
}
