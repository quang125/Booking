package com.example.bookingmeetingroom.service;

import com.example.bookingmeetingroom.exception.MeetingAlreadyPassedException;
import com.example.bookingmeetingroom.exception.MeetingNotFoundException;
import com.example.bookingmeetingroom.exception.RoomAlreadyUsedException;
import com.example.bookingmeetingroom.exception.RoomNotFoundException;
import com.example.bookingmeetingroom.model.dto.BookingDTO;
import com.example.bookingmeetingroom.model.dto.ChangeMeetingRoomDTO;
import com.example.bookingmeetingroom.model.dto.MeetingDTO;

import java.util.List;

public interface MeetingService {
    public boolean cancalMeeting(Long meetingId) throws MeetingNotFoundException, MeetingAlreadyPassedException;
    public List<MeetingDTO> getAllBookedMeeting();
    public MeetingDTO bookMeeting(BookingDTO bookingDTO) throws RoomNotFoundException, RoomAlreadyUsedException;
    public MeetingDTO changeMeetingRoom(ChangeMeetingRoomDTO changeRoomDTO) throws RoomNotFoundException, RoomAlreadyUsedException;

}
