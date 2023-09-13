package com.example.bookingmeetingroom.controller;

import com.example.bookingmeetingroom.exception.MeetingAlreadyPassedException;
import com.example.bookingmeetingroom.exception.MeetingNotFoundException;
import com.example.bookingmeetingroom.exception.RoomAlreadyUsedException;
import com.example.bookingmeetingroom.exception.RoomNotFoundException;
import com.example.bookingmeetingroom.model.dto.BookingDTO;
import com.example.bookingmeetingroom.model.dto.ChangeMeetingRoomDTO;
import com.example.bookingmeetingroom.model.dto.MeetingDTO;
import com.example.bookingmeetingroom.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/meetings")
public class MeetingController {
    @Autowired
    private MeetingService meetingService;


    @PostMapping("/booking")
    public ResponseEntity<MeetingDTO>bookingMeetingRoom(@Valid @RequestBody BookingDTO bookingDTO) throws RoomNotFoundException, RoomAlreadyUsedException {
        MeetingDTO meetingDTO=meetingService.bookMeeting(bookingDTO);
        if(meetingDTO==null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
        return ResponseEntity.ok(meetingDTO);
    }
    @PutMapping("/cancel/{meetingId}")
    public ResponseEntity<String>cancelMeeting(@PathVariable String meetingId) throws MeetingNotFoundException, MeetingAlreadyPassedException, ParseException {
        boolean canCancel= meetingService.cancelMeeting(meetingId);
        if(canCancel==false) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Can not cancel meeting");
        return ResponseEntity.ok("Meeting canceled");
    }
    @GetMapping("/show")
    public ResponseEntity<List<MeetingDTO>>getAllBookedMeeting(){
        List<MeetingDTO>meetingDTOList=meetingService.getAllBookedMeeting();
        return ResponseEntity.ok(meetingDTOList);
    }
    @PutMapping("/change")
    public ResponseEntity<MeetingDTO>changeMeetingRoom(@Valid @RequestBody ChangeMeetingRoomDTO changeMeetingRoomDTO) throws RoomNotFoundException, RoomAlreadyUsedException {
        MeetingDTO meetingDTO=meetingService.changeMeetingRoom(changeMeetingRoomDTO);
        if(meetingDTO==null) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        return ResponseEntity.ok(meetingDTO);
    }
}
