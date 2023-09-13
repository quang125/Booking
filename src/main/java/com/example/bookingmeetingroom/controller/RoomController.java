package com.example.bookingmeetingroom.controller;

import com.example.bookingmeetingroom.exception.RoomAlreadyUsedException;
import com.example.bookingmeetingroom.exception.RoomNotFoundException;
import com.example.bookingmeetingroom.model.dto.ChangeRoomInformationDTO;
import com.example.bookingmeetingroom.model.dto.MeetingScheduleDTO;
import com.example.bookingmeetingroom.model.dto.ReportDTO;
import com.example.bookingmeetingroom.model.dto.RoomFormDTO;
import com.example.bookingmeetingroom.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @PostMapping("/add")
    public ResponseEntity<RoomFormDTO>addRoom(@Valid @RequestBody RoomFormDTO roomFormDTO) throws RoomAlreadyUsedException {
        RoomFormDTO newRoomFormDTO=roomService.addRoom(roomFormDTO);
        if(newRoomFormDTO==null) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        return ResponseEntity.ok(newRoomFormDTO);
    }
    @PutMapping("/change")
    public ResponseEntity<RoomFormDTO>changeRoomInformation(@Valid @RequestBody RoomFormDTO roomFormDTO){
        return ResponseEntity.ok(roomService.changeRoomInformation(roomFormDTO));
    }
    @PutMapping("/delete/{roomID}")
    public ResponseEntity<String>deleteRoom(@PathVariable String roomID) throws RoomNotFoundException {
        boolean canDelete= roomService.deleteRoom(roomID);
        if(canDelete==false){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Can not delete this room");
        }
        return ResponseEntity.ok("Room deleted");
    }
    @GetMapping("/show")
    public ResponseEntity<List<MeetingScheduleDTO>>showAllRoomSchedule(){
        List<MeetingScheduleDTO>meetingScheduleDTOList=roomService.getAllRoomMeetingSchedule();
        return ResponseEntity.ok(meetingScheduleDTOList);
    }
    @GetMapping("/report")
    public ResponseEntity<Map<String, ReportDTO>> monthlyReport(){
        Map<String,ReportDTO>reportDTOMap=roomService.MonthlyRoomUsageReport();
        return ResponseEntity.ok(reportDTOMap);
    }
}
