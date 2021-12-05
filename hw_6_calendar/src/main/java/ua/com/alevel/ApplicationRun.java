package ua.com.alevel;

import ua.com.alevel.controller.CalendarController;

import java.text.ParseException;

public class ApplicationRun {

    public static void main(String[] args) throws ParseException {

        new CalendarController().run();
    }
}
