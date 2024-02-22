package com.ozgurokanozdal.habitTracker.exceptions;

public class ContentNotFoundException extends RuntimeException{
    public ContentNotFoundException() {
        super("Content not found! It may have been deleted!");
    }

    public ContentNotFoundException(String message) {
        super(message);
    }

    public ContentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContentNotFoundException(Throwable cause) {
        super(cause);
    }
}
