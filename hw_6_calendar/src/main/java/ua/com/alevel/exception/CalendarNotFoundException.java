package ua.com.alevel.exception;

public class CalendarNotFoundException extends RuntimeException{
    public CalendarNotFoundException(String message) {
        super(message);
    }
}
