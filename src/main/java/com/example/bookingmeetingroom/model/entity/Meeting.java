package com.example.bookingmeetingroom.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    @Size(min = 3, max = 50)
    private String projectName;
    private String status;
    @FutureOrPresent
    private Date startTime;
    private Date endTime;
    @NotNull
    private int numberAttend;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id")
    private Room room;

    public Meeting(String projectName, String status, Date startTime, Date endTime, User currentLoggedInUser, Room room) {
        this.projectName=projectName;
        this.status=status;
        this.startTime=startTime;
        this.endTime=endTime;
        this.user=currentLoggedInUser;
        this.room=room;
    }
    public Meeting(Long id, String projectName, String status, Date startTime, Date endTime, User currentLoggedInUser, Room room) {
        this.id=id;
        this.projectName=projectName;
        this.status=status;
        this.startTime=startTime;
        this.endTime=endTime;
        this.user=currentLoggedInUser;
        this.room=room;
    }
}
