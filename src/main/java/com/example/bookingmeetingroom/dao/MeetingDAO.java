package com.example.bookingmeetingroom.dao;

import com.example.bookingmeetingroom.model.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeetingDAO extends JpaRepository<Meeting,Long> {
    public List<Meeting>findAll();
    public Optional<Meeting>findById(Long meetingId);
    public List<Meeting>findByRoomId(Long roomId);
    public List<Meeting>findByUserId(Long userId);
}
