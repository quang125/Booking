package com.example.bookingmeetingroom.exception;

public class RoomAlreadyUsedException extends Exception{
    public RoomAlreadyUsedException() {
        super();
    }

    public RoomAlreadyUsedException(String message) {
        super(message);
    }
}
