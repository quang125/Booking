package com.example.bookingmeetingroom.exception;

public class MeetingNotFoundException extends Exception{
    public MeetingNotFoundException() {
        super();
    }

    public MeetingNotFoundException(String message) {
        super(message);
    }
}
