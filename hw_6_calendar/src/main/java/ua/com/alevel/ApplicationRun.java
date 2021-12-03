package ua.com.alevel;

import ua.com.alevel.model.Calendar;

import java.text.ParseException;
import java.time.LocalDateTime;

public class ApplicationRun {

    public static void main(String[] args) throws ParseException {

        Calendar calendar = Calendar.Builder()
                .setMonth(5)
                .setYear(2012)
                .setDay(23)
                        .setHour(11)
                                .setMinute(31)
                                        .setSecond(33).build();


        calendar.plusMonths(33);
        System.out.println(calendar);

        LocalDateTime localDateTime = LocalDateTime.of(2012,5, 23, 11,31, 33 );
        localDateTime.plusMonths(33);
        System.out.println(localDateTime);


        System.out.println(Calendar.formattingCalendar(calendar));





    }
}
