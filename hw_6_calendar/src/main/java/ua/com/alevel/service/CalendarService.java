package ua.com.alevel.service;

import org.apache.commons.lang3.StringUtils;
import ua.com.alevel.enumeration.Month;
import ua.com.alevel.exception.CalendarNotFoundException;
import ua.com.alevel.exception.PatternNotFoundException;
import ua.com.alevel.resources.impl.Calendar;
import ua.com.alevel.resources.impl.FormatCalendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import static ua.com.alevel.StringerUtil.print;
import static ua.com.alevel.constant.CalendarPatterConst.*;
import static ua.com.alevel.constant.ExceptionConst.*;
import static ua.com.alevel.constant.InfoConst.*;

public class CalendarService {

    private Calendar calendar;
    private FormatCalendarService formatterService = new FormatCalendarService();
    private String pattern;


    public void createPattern(BufferedReader reader) throws IOException {
        pattern = formatterService.chooseFormat(reader);
        try {
            if (StringUtils.isEmpty(pattern) || pattern == null) {
                throw new PatternNotFoundException(NOT_CHOOSE_PATTERN);
            }
        } catch (PatternNotFoundException e) {
            print(e.getMessage());
        }
    }

    public void createCalendar(BufferedReader reader) throws IOException {
        if (isHasCalendar()) {
            boolean updateCalendar = isUpdateCalendar(reader);
            if (updateCalendar == false) {
                return;
            }
        }
        print(String.format(INFO_CREATE_DATE, pattern));
        try {
            isNotCreatePattern();
            calendar = isValidWithPattern(reader.readLine());
            print(DATE_WAS_CREATE_SUCCESSFULL);
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
            printWarn(NOT_CHOOSE_PATTERN);
        }
    }

    private boolean isUpdateCalendar(BufferedReader reader) throws IOException {
        print("You have, a calendar. Do you want create new date? \n" +
                "1 - Create new calendar\n" +
                "2 - Close menu, and work with this date");
        switch (reader.readLine()) {
            case "1":
                return true;
            case "2":
                return false;
            default:
                printWarn(BAD_ENTRY);
        }
        return false;
    }

    private boolean isHasCalendar() {
        if (calendar != null) {
            return true;
        }
        return false;
    }

    private boolean isNotCreatedCalendar() {
        if (calendar != null) {
            return true;
        }
        throw new CalendarNotFoundException(NOT_CREATE_CALENDAR);
    }

    private Calendar isValidWithPattern(String entryUser) {
        if (isValidEntry(entryUser)) {
            return validationEntry(entryUser);
        }
        throw new PatternNotFoundException(USER_ADD_DATE_WITH_PATTERN);
    }

    private Calendar validationEntry(String entryUser) {
        if (pattern.equals(DOUBLE_SYMBOL_DAY_AND_MONTH_YEAR)) {
            if (entryUser.length() > 10 && !entryUser.contains("/")) {
                throw new PatternNotFoundException(String.format(NOT_VALID_PATTERN, pattern));
            }
            return createStringToCalendarFirst(entryUser);
        } else if (pattern.equals(SIMPLE_SYMBOL_MONTH_AND_DAY_YEAR)) {
            if (!entryUser.contains("/") || entryUser.contains(":")) {
                throw new PatternNotFoundException(String.format(NOT_VALID_PATTERN, pattern));
            }
            return createStringToCalendarSecond(entryUser);
        } else if (pattern.equals(THREE_SYMBOL_MONTH_AND_ONE_SYMBOL_DAY_YEAR)) {
            if (entryUser.contains(":") || entryUser.contains("/") || entryUser.contains("-")) {
                throw new PatternNotFoundException(String.format(NOT_VALID_PATTERN, pattern));
            }
            return createStringToCalendarThird(entryUser);
        } else if (pattern.equals(DOUBLE_SYMBOL_DAY_AND_THREE_SYMBOL_DAY_YEAR)) {
            if (!entryUser.contains(":") || entryUser.contains("/") || entryUser.contains("-")) {
                throw new PatternNotFoundException(String.format(NOT_VALID_PATTERN, pattern));
            }
            return createStringToCalendarFourth(entryUser);
        }
        throw new PatternNotFoundException(NOT_VALID);
    }

    private Calendar createStringToCalendarFourth(String entryUser) {
        String[] validation = entryUser.split(" ");
        long day = Long.parseLong(validation[0]);
        long year = Long.parseLong(validation[2]);
        Month findMonth = Arrays.stream(Month.values()).filter(m -> m.name().toLowerCase().equals(validation[1].toLowerCase())).findFirst().get();
        long month = findMonth.getNumberMonth();
        String[] h = validation[3].split(":");
        long hour = Long.parseLong(h[0]);
        long minute = Long.parseLong(h[1]);
        isValidEntryDate(year, month, day, hour, minute, 0, 0);
        return Calendar.of(year, month, day, hour, minute, 0).build();
    }

    private Calendar createStringToCalendarThird(String entryUser) {
        String[] validation = entryUser.split(" ");
        long day = Long.parseLong(validation[1]);
        long year = Long.parseLong(validation[2]);
        Month findMonth = Arrays.stream(Month.values()).filter(m -> m.name().toLowerCase().equals(validation[0].toLowerCase())).findFirst().get();
        long month = findMonth.getNumberMonth();
        isValidEntryDate(year, month, day, 0, 0, 0, 0);
        return Calendar.of(year, month, day).build();
    }

    private Calendar createStringToCalendarSecond(String entryUser) {
        String[] validation = entryUser.split("/");
        long month = Long.parseLong(validation[0]);
        long day = Long.parseLong(validation[1]);
        long year = Long.parseLong(validation[2]);
        isValidEntryDate(year, month, day, 0, 0, 0, 0);
        return Calendar.of(year, month, day).build();
    }

    private Calendar createStringToCalendarFirst(String entryUser) {
        String[] validation = entryUser.split("/");
        long day = Long.parseLong(validation[0]);
        long month = Long.parseLong(validation[1]);
        long year = Long.parseLong(validation[2]);
        isValidEntryDate(year, month, day, 0, 0, 0, 0);
        return Calendar.of(year, month, day, 0, 0, 0, 0).build();
    }

    private void isValidEntryDate(long year,
                                  long month,
                                  long day,
                                  long hour,
                                  long minute,
                                  long second,
                                  long milliseconds) {
        if (
                year > Long.MAX_VALUE || year < 0 ||
                        month > 12 || month < 0 ||
                        day > 31 || day < 0 ||
                        hour > 24 || hour < 0 ||
                        minute > 60 || minute < 0 ||
                        second > 60 || second < 0 ||
                        milliseconds > 1000 || milliseconds < 0
        ) {
            throw new PatternNotFoundException(BIG_SIZE_NUMBER);
        }
    }

    private void isValidUpdateDate(long year,
                                   long month,
                                   long day,
                                   long hour,
                                   long minute,
                                   long second,
                                   long milliseconds) {
        if (
                year >= Long.MAX_VALUE || year < 0 ||
                        month >= Long.MAX_VALUE || month < 0 ||
                        day >= Long.MAX_VALUE || day < 0 ||
                        hour >= Long.MAX_VALUE || hour < 0 ||
                        minute >= Long.MAX_VALUE || minute < 0 ||
                        second >= Long.MAX_VALUE || second < 0 ||
                        milliseconds >= Long.MAX_VALUE || milliseconds < 0
        ) {
            throw new PatternNotFoundException(BIG_SIZE_NUMBER);
        }
    }


    private boolean isValidEntry(String entry) {
        if (StringUtils.isEmpty(entry) ||
                Objects.isNull(entry) ||
                entry.length() < 1) {
            throw new NullPointerException();
        }
        return true;
    }

    private boolean isNotCreatePattern() {
        if (pattern.length() < 0 ||
                pattern == null) {
            throw new PatternNotFoundException(NOT_CHOOSE_PATTERN);
        }
        return false;
    }

    public void plusDays(BufferedReader reader)
            throws IOException {

        try {
            isNotCreatePattern();
            isNotCreatedCalendar();
            print("Entry the days, that add to date: ");
            String d = reader.readLine();
            isValidEntry(d);
            long days = isNumber(d);
            isValidUpdateDate(0, 0, days, 0, 0, 0, 0);
            calendar.plusDays(days);
        } catch (CalendarNotFoundException e) {
            printWarn(e.getMessage());
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
            printWarn(BAD_ENTRY);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void plusMonth(BufferedReader reader)
            throws IOException {
        try {
            isNotCreatePattern();
            isNotCreatedCalendar();
            print("Entry the month, that add to date: ");
            String m = reader.readLine();
            isValidEntry(m);
            long month = isNumber(m);
            isValidUpdateDate(0, month, 0, 0, 0, 0, 0);
            calendar.plusMonths(month);
        } catch (CalendarNotFoundException e) {
            printWarn(e.getMessage());
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
            printWarn(BAD_ENTRY);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void plusYears(BufferedReader reader)
            throws IOException {
        try {
            isNotCreatePattern();
            isNotCreatedCalendar();
            print("Entry the years, that add to date: ");
            String y = reader.readLine();
            isValidEntry(y);
            long year = isNumber(y);
            isValidUpdateDate(year, 0, 0, 0, 0, 0, 0);
            calendar.plusYears(year);
        } catch (CalendarNotFoundException e) {
            printWarn(e.getMessage());
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
            printWarn(BAD_ENTRY);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void minusYears(BufferedReader reader)
            throws IOException {
        try {
            isNotCreatePattern();
            isNotCreatedCalendar();
            print("Entry the year, that minus from date: ");
            String y = reader.readLine();
            isValidEntry(y);
            long year = isNumber(y);
            isValidUpdateDate(year, 0, 0, 0, 0, 0, 0);
            calendar.minusYears(year);
        } catch (CalendarNotFoundException e) {
            printWarn(e.getMessage());
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
            printWarn(BAD_ENTRY);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void minusDays(BufferedReader reader)
            throws IOException {

        try {
            isNotCreatePattern();
            isNotCreatedCalendar();
            print("Entry the days, that minus from date: ");
            String d = reader.readLine();
            isValidEntry(d);
            long days = isNumber(d);
            isValidUpdateDate(0, 0, days, 0, 0, 0, 0);
            calendar.minusDays(days);
        } catch (CalendarNotFoundException e) {
            printWarn(e.getMessage());
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
            printWarn(BAD_ENTRY);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void minusMonth(BufferedReader reader)
            throws IOException {
        try {
            isNotCreatePattern();
            isNotCreatedCalendar();
            print("Entry the month, that minus from date: ");
            String m = reader.readLine();
            isValidEntry(m);
            long month = isNumber(m);
            isValidUpdateDate(0, month, 0, 0, 0, 0, 0);
            calendar.minusMonths(month);
        } catch (CalendarNotFoundException e) {
            printWarn(e.getMessage());
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
            printWarn(BAD_ENTRY);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void plusHours(BufferedReader reader)
            throws IOException {

        try {
            isNotCreatePattern();
            isNotCreatedCalendar();
            print("Entry the hours, that add to date: ");
            String h = reader.readLine();
            isValidEntry(h);
            long hours = isNumber(h);
            isValidUpdateDate(0, 0, 0, hours, 0, 0, 0);
            calendar.plusHours(hours);
        } catch (CalendarNotFoundException e) {
            printWarn(e.getMessage());
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
            printWarn(BAD_ENTRY);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void plusMinutes(BufferedReader reader)
            throws IOException {

        try {
            isNotCreatePattern();
            isNotCreatedCalendar();
            print("Entry the hours, that add to date: ");
            String m = reader.readLine();
            isValidEntry(m);
            long minute = isNumber(m);
            isValidUpdateDate(0, 0, 0, 0, minute, 0, 0);
            calendar.plusMinutes(minute);
        } catch (CalendarNotFoundException e) {
            printWarn(e.getMessage());
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
            printWarn(BAD_ENTRY);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void plusSeconds(BufferedReader reader)
            throws IOException {

        try {
            isNotCreatePattern();
            isNotCreatedCalendar();
            print("Entry the hours, that add to date: ");
            String s = reader.readLine();
            isValidEntry(s);
            long second = isNumber(s);
            isValidUpdateDate(0, 0, 0, 0, 0, second, 0);
            calendar.plusSeconds(second);
        } catch (CalendarNotFoundException e) {
            printWarn(e.getMessage());
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
            printWarn(BAD_ENTRY);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void plusMilliseconds(BufferedReader reader)
            throws IOException {

        try {
            isNotCreatePattern();
            isNotCreatedCalendar();
            print("Entry the hours, that add to date: ");
            String ms = reader.readLine();
            isValidEntry(ms);
            long milliseconds = isNumber(ms);
            isValidUpdateDate(0, 0, 0, 0, 0, 0, milliseconds);
            calendar.plusMilliseconds(milliseconds);
        } catch (CalendarNotFoundException e) {
            printWarn(e.getMessage());
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
            printWarn(BAD_ENTRY);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void minusHours(BufferedReader reader)
            throws IOException {

        try {
            isNotCreatePattern();
            isNotCreatedCalendar();
            print("Entry the hours, that minus from date: ");
            String h = reader.readLine();
            isValidEntry(h);
            long hours = isNumber(h);
            isValidUpdateDate(0, 0, 0, hours, 0, 0, 0);
            calendar.minusHours(hours);
        } catch (CalendarNotFoundException e) {
            printWarn(e.getMessage());
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
            printWarn(BAD_ENTRY);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void minusMinutes(BufferedReader reader)
            throws IOException {

        try {
            isNotCreatePattern();
            isNotCreatedCalendar();
            print("Entry the hours, that minus from date: ");
            String m = reader.readLine();
            isValidEntry(m);
            long minute = isNumber(m);
            isValidUpdateDate(0, 0, 0, 0, minute, 0, 0);
            calendar.minusMinutes(minute);
        } catch (CalendarNotFoundException e) {
            printWarn(e.getMessage());
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
            printWarn(BAD_ENTRY);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void minusSeconds(BufferedReader reader)
            throws IOException {

        try {
            isNotCreatePattern();
            isNotCreatedCalendar();
            print("Entry the hours, that minus from date: ");
            String s = reader.readLine();
            isValidEntry(s);
            long second = isNumber(s);
            isValidUpdateDate(0, 0, 0, 0, 0, second, 0);
            calendar.minusSeconds(second);
        } catch (CalendarNotFoundException e) {
            printWarn(e.getMessage());
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
            printWarn(BAD_ENTRY);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void minusMilliseconds(BufferedReader reader)
            throws IOException {

        try {
            isNotCreatePattern();
            isNotCreatedCalendar();
            print("Entry the hours, that minus from date: ");
            String ms = reader.readLine();
            isValidEntry(ms);
            long milliseconds = isNumber(ms);
            isValidUpdateDate(0, 0, 0, 0, 0, 0, milliseconds);
            calendar.minusMilliseconds(milliseconds);
        } catch (CalendarNotFoundException e) {
            printWarn(e.getMessage());
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
            printWarn(BAD_ENTRY);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void compareDate(BufferedReader reader)
            throws IOException, InterruptedException {
        try {
            isNotCreatePattern();
            getTime();
            print(INFO_CREATE_COMPARE_DATE + FormatCalendar.formattingCalendar(calendar));
            print(String.format(INFO_CREATE_DATE, pattern));
            Calendar compareCalendar = isValidWithPattern(reader.readLine());
            thinkProgram();
            print(COMPARE_DATE_SUCCESSFULL);
            viewDate(calendar.compareCalendar(compareCalendar));
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
            printWarn(NOT_CREATE_CALENDAR);
        }
    }

    private void thinkProgram() throws InterruptedException {
        print("I create your date:\n waiting");
        for (int i = 0; i < 5; i++) {
            Thread.sleep(500);
            System.out.print(".");
        }
    }

    public void getTime() {
        try {
            isNotCreatePattern();
            viewDate(FormatCalendar.chooseFormatDate(calendar, pattern));
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
            printWarn(NOT_CREATE_CALENDAR);
        }
    }

    public void getFullDateTime() {
        try {
            isNotCreatePattern();
            viewDate(FormatCalendar.formattingCalendar(calendar));
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
            printWarn(NOT_CREATE_CALENDAR);
        }
    }

    private void viewDate(String str) {
        System.out.println(
                "------------------------------------\n" +
                        "\t\t-" + str + "-\n" +
                        "------------------------------------"
        );
    }

    public static void printWarn(String str) {
        System.out.println("_____________________________________________\n" +
                "        *********Warning********\n"
                + " |||||----" + str + "----|||||\n"
                + "_____________________________________________");
    }


    private long isNumber(String number) {
        try {
            long n = Long.parseLong(number);
            if (n == Long.MAX_VALUE) {
                throw new NumberFormatException(BIG_SIZE_NUMBER);
            }
            return n;
        } catch (NumberFormatException e) {
        }
        throw new NumberFormatException(IS_NOT_NUMBER + number);
    }

}

