package ua.com.alevel.resources.impl;

import ua.com.alevel.enumeration.Month;

import java.util.Arrays;

import static ua.com.alevel.constant.CalendarPatterConst.*;

public class FormatCalendar {


    public static String formattingCalendar(Calendar calendar) {
        String format = "%s year, %s month, %s days, %s hours, %s minutes, %s seconds, %s milliseconds";
        return String.format(
                format,
                calendar.getYear(),
                calendar.getMonth(),
                calendar.getDay(),
                calendar.getHour(),
                calendar.getMinute(),
                calendar.getSecond(),
                calendar.getMillsecond()
        );
    }

    public static String chooseFormatDate(Calendar calendar ,String chooseFormat) {
     return choose(calendar, chooseFormat);
    }

    private static String choose(Calendar calendar, String chooseFormat) {
        switch (chooseFormat) {
            case DOUBLE_SYMBOL_DAY_AND_MONTH_YEAR: return formatData(calendar,DOUBLE_SYMBOL_DAY_AND_MONTH_YEAR);
            case SIMPLE_SYMBOL_MONTH_AND_DAY_YEAR: return formatData(calendar,SIMPLE_SYMBOL_MONTH_AND_DAY_YEAR );
            case THREE_SYMBOL_MONTH_AND_ONE_SYMBOL_DAY_YEAR: return formatData(calendar, THREE_SYMBOL_MONTH_AND_ONE_SYMBOL_DAY_YEAR);
            case DOUBLE_SYMBOL_DAY_AND_THREE_SYMBOL_DAY_YEAR: return formatData(calendar, DOUBLE_SYMBOL_DAY_AND_THREE_SYMBOL_DAY_YEAR);
        }
        return "Bad Format pattern";
    }

    private static String formatData(Calendar calendar, String pattern) {
        if (pattern.contains(DOUBLE_SYMBOL_DAY_AND_MONTH_YEAR)) {
           return takeFormatDate(calendar, FORMATE_DATE);
        } else if (pattern.contains(SIMPLE_SYMBOL_MONTH_AND_DAY_YEAR)) {
          return takeFormatDateShort(calendar, FORMATE_DATE_SHORT);
        } else if (pattern.contains(THREE_SYMBOL_MONTH_AND_ONE_SYMBOL_DAY_YEAR)) {
           return takeFormatDateWordMonth(calendar, FORMATE_DATE_WITH_WORD_MONTH);
        }
        return takeDateWithFormatStandart(calendar, FORMATE_DATE_TIME_STANDART);
    }

    private static String takeDateWithFormatStandart(Calendar calendar, String pattern) {
        Month month = Arrays.stream(Month.values()).filter(m -> m.getNumberMonth() == calendar.getMonth()).findFirst().get();
        String dd = calendar.getDay() > 9 ? String.valueOf(calendar.getDay()) : "0" + calendar.getDay();
        String mm = calendar.getMonth() > 9 ? String.valueOf(calendar.getMonth()) : "0" + calendar.getMonth();
        String yyyy = calendar.getYear() > 999 ? String.valueOf(calendar.getYear()) : "0" + calendar.getYear();
        String h = calendar.getHour() > 9 ? String.valueOf(calendar.getHour()) : "0" + calendar.getHour();
        String m = calendar.getMinute() > 9 ? String.valueOf(calendar.getMinute()) : "0" + calendar.getMinute();
        if (calendar.getYear() < 99) {
            yyyy = "00" + calendar.getYear();
        }
        if (calendar.getYear() < 9) {
            yyyy = "000" + calendar.getYear();
        }

        return String.format(pattern, dd, month.name(), yyyy, h, m);
    }

    private static String takeFormatDateWordMonth(Calendar calendar, String pattern) {
        Month month = Arrays.stream(Month.values()).filter(m -> m.getNumberMonth() == calendar.getMonth()).findFirst().get();
        String yyyy = calendar.getYear() > 999 ? String.valueOf(calendar.getYear()) : "0" + calendar.getYear();
        if (calendar.getYear() < 99) {
            yyyy = "00" + calendar.getYear();
        }
        if (calendar.getYear() < 9) {
            yyyy = "000" + calendar.getYear();
        }
       return String.format(pattern, month.name(), calendar.getDay(), yyyy);

    }

    private static String takeFormatDate(Calendar calendar, String pattern) {
        String dd = calendar.getDay() > 9 ? String.valueOf(calendar.getDay()) : "0" + calendar.getDay();
        String mm = calendar.getMonth() > 9 ? String.valueOf(calendar.getMonth()) : "0" + calendar.getMonth();
        String yyyy = calendar.getYear() > 999 ? String.valueOf(calendar.getYear()) : "0" + calendar.getYear();
        if (calendar.getYear() < 99) {
            yyyy = "00" + calendar.getYear();
        }
        if (calendar.getYear() < 9) {
           yyyy = "000" + calendar.getYear();
        }
        return String.format(pattern, dd, mm, yyyy);
    }

    private static String takeFormatDateShort(Calendar calendar, String pattern) {
        String yyyy = calendar.getYear() > 999 ? String.valueOf(calendar.getYear()) : "0" + calendar.getYear();
        if (calendar.getYear() < 99) {
            yyyy = "00" + calendar.getYear();
        }
        if (calendar.getYear() < 9) {
            yyyy = "000" + calendar.getYear();
        }
        return String.format(pattern, calendar.getMonth(), calendar.getDay(), yyyy);
    }
}
