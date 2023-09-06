package com.example.bookingmeetingroom.service;

import com.example.bookingmeetingroom.dao.MeetingDAO;
import com.example.bookingmeetingroom.dao.RoomDAO;
import com.example.bookingmeetingroom.exception.RoomNotFoundException;
import com.example.bookingmeetingroom.model.dto.ChangeRoomInformationDTO;
import com.example.bookingmeetingroom.model.dto.MeetingScheduleDTO;
import com.example.bookingmeetingroom.model.dto.ReportDTO;
import com.example.bookingmeetingroom.model.dto.RoomFormDTO;
import com.example.bookingmeetingroom.model.entity.Meeting;
import com.example.bookingmeetingroom.model.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoomServiceImpl implements RoomService{
    @Autowired
    private MeetingDAO meetingDAO;
    @Autowired
    private RoomDAO roomDAO;
    @Override
    public List<MeetingScheduleDTO> getAllRoomMeetingSchedule() {
        List<Meeting>meetingList=meetingDAO.findAll();
        List<MeetingScheduleDTO>meetingScheduleDTOList=new ArrayList<>();
        for(Meeting meeting:meetingList){
            if(meeting.getRoom().isDeleted()) continue;
            meetingScheduleDTOList.add(new MeetingScheduleDTO(meeting.getRoom().getRoomName(), meeting.getStartTime(),
                    meeting.getEndTime(), meeting.getUser().getName()));
        }
        return meetingScheduleDTOList;
    }

    @Override
    public RoomFormDTO addRoom(RoomFormDTO roomFormDTO) throws RoomNotFoundException {
        Optional<Room>optionalRoom=roomDAO.findByRoomName(roomFormDTO.getRoomName());
        if(optionalRoom.isPresent()){
            if(!optionalRoom.get().isDeleted()) throw new RoomNotFoundException(roomFormDTO.getRoomName());
            else{
                Room oldRoom=optionalRoom.get();
                oldRoom.setDeleted(false);
                roomDAO.save(oldRoom);
                return roomFormDTO;
            }
        }
        Room room=new Room(roomFormDTO.getCapacity(), roomFormDTO.getRoomName(),  false,new ArrayList<>());
        roomDAO.save(room);
        return roomFormDTO;
    }

    @Override
    public boolean deleteRoom(Long roomId){
        Optional<Room>optionalRoom=roomDAO.findById(roomId);
        if(!optionalRoom.isPresent()||optionalRoom.get().isDeleted()) return false;
        Room room=optionalRoom.get();
        room.setDeleted(true);
        roomDAO.save(room);
        return true;
    }

    @Override
    public RoomFormDTO changeRoomInformation(RoomFormDTO roomFormDTO) {
        Room room=roomDAO.findByRoomName(roomFormDTO.getRoomName()).get();
        room.setCapacity(room.getCapacity());
        roomDAO.save(room);
        return roomFormDTO;
    }

    @Override
    public HashMap<String,List<ReportDTO>> MonthlyRoomUsageReport() {
        List<Meeting>meetingList=meetingDAO.findAll();
        HashMap<String,List<ReportDTO>>reportDTO=new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        Date date30DaysAgo = calendar.getTime();
        for(Meeting meeting:meetingList){
            if(meeting.getEndTime().before(new Date())&&meeting.getEndTime().after(date30DaysAgo)){
                if(!reportDTO.containsKey(meeting.getRoom().getRoomName())) reportDTO.put(meeting.getRoom().getRoomName(),new ArrayList<>());
                reportDTO.get(meeting.getRoom().getRoomName()).add(new ReportDTO())
            }
        }
    }
}
