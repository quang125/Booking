package com.example.bookingmeetingroom.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    @NotNull
    @Size(min=3, max = 50)
    private String projectName;
    private String roomName;
    @NotNull
    private int numberAttend;
    @FutureOrPresent
    private Date startTime;
    private Date endTime;
}
