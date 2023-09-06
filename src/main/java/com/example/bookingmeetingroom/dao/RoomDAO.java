package com.example.bookingmeetingroom.dao;

import com.example.bookingmeetingroom.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomDAO extends JpaRepository<Room,Long> {
    public Optional<Room> findByRoomName(String roomName);
}
