package com.example.bookingmeetingroom.dao;

import com.example.bookingmeetingroom.model.entity.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomDAO extends MongoRepository<Room,String> {
    public Optional<Room> findByRoomName(String roomName);
}
