package com.example.bookingmeetingroom.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Room")
public class Room {
    @Id
    private String id;
    @NotNull
    private int capacity;
    private String roomName;
    private boolean isDeleted;
    public Room(int capacity, String roomName, boolean isDeleted) {
        this.capacity = capacity;
        this.roomName = roomName;
        this.isDeleted = isDeleted;
    }
}
