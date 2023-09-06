package com.example.bookingmeetingroom.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    private int capacity;
    @Column(unique = true)
    private String roomName;
    private boolean isDeleted;
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<Meeting> meetingList;
    public Room(int capacity, String roomName, boolean isDeleted, List<Meeting> meetingList) {
        this.capacity = capacity;
        this.roomName = roomName;
        this.isDeleted = isDeleted;
        this.meetingList = meetingList;
    }
}
