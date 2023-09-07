package com.example.bookingmeetingroom.service;

import com.example.bookingmeetingroom.dao.MeetingDAO;
import com.example.bookingmeetingroom.dao.RoomDAO;
import com.example.bookingmeetingroom.exception.MeetingAlreadyPassedException;
import com.example.bookingmeetingroom.exception.MeetingNotFoundException;
import com.example.bookingmeetingroom.exception.RoomAlreadyUsedException;
import com.example.bookingmeetingroom.exception.RoomNotFoundException;
import com.example.bookingmeetingroom.model.dto.BookingDTO;
import com.example.bookingmeetingroom.model.dto.ChangeMeetingRoomDTO;
import com.example.bookingmeetingroom.model.dto.MeetingDTO;
import com.example.bookingmeetingroom.model.entity.Meeting;
import com.example.bookingmeetingroom.model.entity.Room;
import com.example.bookingmeetingroom.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class MeetingServiceImpl implements MeetingService{
    @Autowired
    private MeetingDAO meetingDAO;
    @Autowired
    private AuthService authService;
    @Autowired
    private RoomDAO roomDAO;
    @Override
    public boolean cancelMeeting(Long meetingId) throws MeetingNotFoundException, MeetingAlreadyPassedException, ParseException {
        Optional<Meeting> optionalMeeting=meetingDAO.findById(meetingId);
        if(!optionalMeeting.isPresent()) throw new MeetingNotFoundException();
        Meeting meeting=optionalMeeting.get();
        User user=authService.getCurrentLoggedInUser();
        if(user.getRole().equals("ROLE_USER")){
            if(!meeting.getUser().getUsername().equals(user.getUsername())) return false;
        }
        if(meeting.getStartTime().after(new Date())){
            meeting.setStatus("canceled");
            meetingDAO.save(meeting);
            return true;
        }
        throw new MeetingAlreadyPassedException();
    }

    @Override
    public List<MeetingDTO> getAllBookedMeeting() {
        User user=authService.getCurrentLoggedInUser();
        List<Meeting>meetingList=meetingDAO.findByUserId(user.getId());
        List<MeetingDTO>meetingDTOList=new ArrayList<>();
        for(Meeting meeting:meetingList){
            if(meeting.getRoom().isDeleted()) continue;
            meetingDTOList.add(new MeetingDTO(meeting.getStartTime(),meeting.getEndTime(),meeting.getRoom().getRoomName(),
                    meeting.getUser().getName()));
        }
        return meetingDTOList;
    }

    @Override
    public MeetingDTO bookMeeting(BookingDTO bookingDTO) throws RoomNotFoundException, RoomAlreadyUsedException {
        if(bookingDTO.getEndTime().before(bookingDTO.getStartTime())||bookingDTO.getStartTime().before(new Date())) return null;
        Optional<Room> optionalRoom=roomDAO.findByRoomName(bookingDTO.getRoomName());
        if(!optionalRoom.isPresent()||optionalRoom.get().isDeleted()) throw new RoomNotFoundException(bookingDTO.getRoomName());
        Room room=optionalRoom.get();
        if(bookingDTO.getNumberAttend()>room.getCapacity()) return null;
        List<Meeting>meetingList=meetingDAO.findByRoomId(room.getId());
        for(Meeting meeting: meetingList){
            if(meeting.getStatus().equals("canceled")) continue;
            if((bookingDTO.getStartTime().before(meeting.getEndTime())&&bookingDTO.getStartTime().after(meeting.getStartTime()))
                    ||(bookingDTO.getEndTime().after(meeting.getStartTime())&&bookingDTO.getStartTime().before(meeting.getEndTime()))
                    ||(bookingDTO.getStartTime().before(meeting.getStartTime())&&bookingDTO.getEndTime().after(meeting.getEndTime())))
                throw new RoomAlreadyUsedException(meeting.getRoom().getRoomName());
        }
        System.out.println(bookingDTO.getNumberAttend());
        Meeting newMeeting=new Meeting(bookingDTO.getProjectName(), "scheduled", bookingDTO.getStartTime(), bookingDTO.getEndTime(), bookingDTO.getNumberAttend(),
                authService.getCurrentLoggedInUser(),room);
        meetingDAO.save(newMeeting);
        return new MeetingDTO(bookingDTO.getStartTime(), bookingDTO.getEndTime(), room.getRoomName(), authService.getCurrentLoggedInUser().getName());
    }

    @Override
    public MeetingDTO changeMeetingRoom(ChangeMeetingRoomDTO changeRoomDTO) throws RoomNotFoundException, RoomAlreadyUsedException {
        Optional<Room>optionalRoom=roomDAO.findByRoomName(changeRoomDTO.getNewRoomName());
        if(!optionalRoom.isPresent()||optionalRoom.get().isDeleted()) throw new RoomNotFoundException(changeRoomDTO.getNewRoomName());
        Room room=optionalRoom.get();
        List<Meeting>meetingList=meetingDAO.findByRoomId(room.getId());
        Meeting oldMeeting=meetingDAO.findById(changeRoomDTO.getMeetingId()).get();
        for(Meeting meeting: meetingList){
            if(meeting.getStatus().equals("canceled")) continue;
            if((oldMeeting.getStartTime().before(meeting.getEndTime())&&oldMeeting.getStartTime().after(meeting.getStartTime()))
                    ||(oldMeeting.getEndTime().after(meeting.getStartTime())&&oldMeeting.getStartTime().before(meeting.getEndTime()))
                    ||(oldMeeting.getStartTime().before(meeting.getStartTime())&&oldMeeting.getEndTime().after(meeting.getEndTime())))
                throw new RoomAlreadyUsedException(meeting.getRoom().getRoomName());
        }
        Meeting newMeeting=new Meeting(oldMeeting.getId(),oldMeeting.getProjectName(), "scheduled", oldMeeting.getStartTime(),
                oldMeeting.getEndTime(), oldMeeting.getNumberAttend(), oldMeeting.getUser(),room);
        meetingDAO.save(newMeeting);
        return new MeetingDTO(oldMeeting.getStartTime(), oldMeeting.getEndTime(), room.getRoomName(), oldMeeting.getUser().getName());
    }
}
