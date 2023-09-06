package com.example.bookingmeetingroom.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingScheduleDTO {
    private String roomName;
    private Date startDate;
    private Date endTime;
    private String status;
}
