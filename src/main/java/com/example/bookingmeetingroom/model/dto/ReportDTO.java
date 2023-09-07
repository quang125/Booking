package com.example.bookingmeetingroom.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    private int numberOrganized;
    private int numberCanceled;
}
