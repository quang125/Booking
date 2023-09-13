package com.example.bookingmeetingroom.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Size;
import java.util.Date;

@Document(collection = "Meeting")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meeting {
    @Id
    private String id;
    @NotNull
    @Size(min = 3, max = 50)
    private String projectName;
    private String status;
    @FutureOrPresent
    private Date startTime;
    private Date endTime;
    @NotNull
    private int numberAttend;

    private User user;

    private Room room;

    public Meeting(String projectName, String status, Date startTime, Date endTime, int numberAttend, User currentLoggedInUser, Room room) {
        this.projectName=projectName;
        this.status=status;
        this.startTime=startTime;
        this.endTime=endTime;
        this.numberAttend=numberAttend;
        this.user=currentLoggedInUser;
        this.room=room;
    }

}
