package com.example.bookingmeetingroom.service;

import com.example.bookingmeetingroom.exception.RoomAlreadyUsedException;
import com.example.bookingmeetingroom.exception.RoomNotFoundException;
import com.example.bookingmeetingroom.model.dto.ReportDTO;
import com.example.bookingmeetingroom.model.dto.RoomFormDTO;
import com.example.bookingmeetingroom.model.dto.MeetingScheduleDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RoomService {
    public List<MeetingScheduleDTO> getAllRoomMeetingSchedule();
    public RoomFormDTO addRoom(RoomFormDTO roomFormDTO) throws RoomAlreadyUsedException;
    public boolean deleteRoom(Long roomId) throws RoomNotFoundException;
    public RoomFormDTO changeRoomInformation(RoomFormDTO roomFormDTO);
    public Map<String,ReportDTO> MonthlyRoomUsageReport();
}
