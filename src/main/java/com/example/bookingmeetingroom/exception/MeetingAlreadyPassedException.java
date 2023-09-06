package com.example.bookingmeetingroom.exception;

public class MeetingAlreadyPassedException extends Exception{
    public MeetingAlreadyPassedException() {
        super();
    }

    public MeetingAlreadyPassedException(String message) {
        super(message);
    }
}
