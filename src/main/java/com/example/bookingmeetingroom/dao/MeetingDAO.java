package com.example.bookingmeetingroom.dao;

import com.example.bookingmeetingroom.model.entity.Meeting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeetingDAO extends MongoRepository<Meeting,String> {
    public List<Meeting>findAll();
    public Optional<Meeting>findById(String meetingId);
    public List<Meeting>findByRoomId(String roomId);
    public List<Meeting>findByUserId(String userId);
}
