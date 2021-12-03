package ua.com.alevel;

import ua.com.alevel.model.Calendar;

import java.text.ParseException;

public class ApplicationRun {

    public static void main(String[] args) throws ParseException {

        Calendar calendar = Calendar.of()
                .month(1)
                .year(2012)
                .day(23)
                        .hour(11)
                                .minute(31)
                                        .second(33).build();

        calendar.plusDays(100);

//        calendar.plusMonths(2);
//        System.out.println(calendar);
//
//        LocalDateTime localDateTime = LocalDateTime.of(2012,5, 23, 11,31, 33 );
//        localDateTime.plusMonths(33);
//        System.out.println(localDateTime);


        System.out.println(Calendar.formattingCalendar(calendar));





    }
}
