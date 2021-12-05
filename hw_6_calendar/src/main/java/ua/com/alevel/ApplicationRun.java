package ua.com.alevel;

import ua.com.alevel.resources.impl.Calendar;
import ua.com.alevel.resources.impl.FormatCalendar;

import java.text.ParseException;

public class ApplicationRun {

    public static void main(String[] args) throws ParseException {

        new CalendarController().run();
    }
}
