package com.example.bookingmeetingroom.controller;

import com.example.bookingmeetingroom.model.dto.BookingDTO;
import com.example.bookingmeetingroom.model.dto.MeetingDTO;
import com.example.bookingmeetingroom.model.entity.Meeting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @PostMapping("/room/booking")
    public ResponseEntity<MeetingDTO>bookingMeetingRoom(@Valid @RequestBody BookingDTO bookingDTO){
        return null;
    }
    @DeleteMapping("/room/cancel/{roomId}")
    public ResponseEntity<String>cancelMeetingRoom(@PathVariable Long roomId){
        return null;
    }
}
