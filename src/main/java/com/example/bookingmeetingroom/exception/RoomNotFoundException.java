package com.example.bookingmeetingroom.exception;

public class RoomNotFoundException extends Exception{
    public RoomNotFoundException() {
        super();
    }

    public RoomNotFoundException(String message) {
        super(message);
    }
}
