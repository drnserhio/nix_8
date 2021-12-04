package ua.com.alevel;

import ua.com.alevel.model.impl.Calendar;

import java.text.ParseException;
import java.time.LocalDateTime;

public class ApplicationRun {

    public static void main(String[] args) throws ParseException {
        LocalDateTime localDateTime = LocalDateTime.of(2012,8, 23, 11,31, 33);
        LocalDateTime localDateTime1 = localDateTime.minusDays(1032);
        System.out.println(localDateTime1);

        Calendar calendar = Calendar.of()
                .month(8)
                .year(2012)
                .day(23)
                        .hour(11)
                                .minute(31)
                                        .second(33).build();

        calendar.minusDays(1032);

//        System.out.println(calendar);
        System.out.println(Calendar.formattingCalendar(calendar));
//








    }
}
