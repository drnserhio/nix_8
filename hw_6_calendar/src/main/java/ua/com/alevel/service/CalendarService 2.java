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

public class CalendarService {

    private Calendar calendar;
    private FormatCalendarService formatterService = new FormatCalendarService();
    private String pattern;


    public void createPattern(BufferedReader reader) throws IOException {
        pattern = formatterService.chooseFormat(reader);
        try {
            if (StringUtils.isEmpty(pattern) || pattern == null) {
                throw new PatternNotFoundException("Don't choose pattern");
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
        print("Entry date: " + "[example -> Your choose this pattern " + pattern + " ]");
        try {
            isNotCreatePattern();
            calendar = isValidWithPattern(reader.readLine());
            print("Date was create succefull");
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
            printWarn("You don't choose pattern!");
        }
    }

    private boolean isUpdateCalendar(BufferedReader reader) throws IOException {
        print("You have, a calendar. Do you want create new date? \n" +
                "1 - Create new calendar\n" +
                "2 - Close menu, and work with has date");
        switch (reader.readLine()) {
            case "1":
                return true;
            case "2":
                return false;
            default:
                printWarn("You entry bad message!");
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
        throw new CalendarNotFoundException("Calendar doesn't creat, please you are going to menu!");
    }

    private Calendar isValidWithPattern(String entryUser) {
        if (isValidEntry(entryUser)) {
            return validationEntry(entryUser);
        }
        throw new PatternNotFoundException("Not valid entry, choose another pattern!");
    }

    private Calendar validationEntry(String entryUser) {
        if (pattern.equals(DOUBLE_SYMBOL_DAY_AND_MONTH_YEAR)) {
            if (entryUser.length() > 10 && !entryUser.contains("/")) {
                throw new PatternNotFoundException("Entry not valid, choose another pattern!");
            }
            return createStringToCalendarFirst(entryUser);
        } else if (pattern.equals(SIMPLE_SYMBOL_MONTH_AND_DAY_YEAR)) {
            if (!entryUser.contains("/") || entryUser.contains(":")) {
                throw new PatternNotFoundException("Entry not valid, choose another pattern!");
            }
            return createStringToCalendarSecond(entryUser);
        } else if (pattern.equals(THREE_SYMBOL_MONTH_AND_ONE_SYMBOL_DAY_YEAR)) {
            if (entryUser.contains(":") || entryUser.contains("/") || entryUser.contains("-")) {
                throw new PatternNotFoundException("Entry not valid, choose another pattern!");
            }
            return createStringToCalendarThird(entryUser);
        } else if (pattern.equals(DOUBLE_SYMBOL_DAY_AND_THREE_SYMBOL_DAY_YEAR)) {
            if (!entryUser.contains(":") || entryUser.contains("/") || entryUser.contains("-")) {
                throw new PatternNotFoundException("Entry not valid, choose another pattern!");
            }
            return createStringToCalendarFourth(entryUser);
        }
        throw new PatternNotFoundException("Not valid entry, choose another pattern!");
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
        return Calendar.of(year, month, day, hour, minute, 0).build();
    }

    private Calendar createStringToCalendarThird(String entryUser) {
        String[] validation = entryUser.split(" ");
        long day = Long.parseLong(validation[1]);
        long year = Long.parseLong(validation[2]);
        Month findMonth = Arrays.stream(Month.values()).filter(m -> m.name().equals(validation[0])).findFirst().get();
        long month = findMonth.getNumberMonth();
        return Calendar.of(year, month, day).build();
    }

    private Calendar createStringToCalendarSecond(String entryUser) {
        String[] validation = entryUser.split("/");
        long month = Long.parseLong(validation[0]);
        long day = Long.parseLong(validation[1]);
        long year = Long.parseLong(validation[2]);
        return Calendar.of(year, month, day).build();
    }

    private Calendar createStringToCalendarFirst(String entryUser) {
        String[] validation = entryUser.split("/");
        long day = Long.parseLong(validation[0]);
        long month = Long.parseLong(validation[1]);
        long year = Long.parseLong(validation[2]);
        return Calendar.of(day, month, year).build();
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
            throw new PatternNotFoundException("You don't choose pattern in start menu");
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
            calendar.plusDays(days);
        } catch (CalendarNotFoundException e) {
            printWarn(e.getMessage());
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void getTime() {
        try {
            isNotCreatePattern();
            viewDate(FormatCalendar.chooseFormatDate(calendar, pattern));
        } catch (PatternNotFoundException e) {
            printWarn(e.getMessage());
        } catch (NullPointerException e) {
            printWarn("You don't choose pattern!");
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
                throw new NumberFormatException("You entry the bigger number!");
            }
            return n;
        } catch (NumberFormatException e) {
        }
        throw new NumberFormatException("You entry bad, it isn't number: " + number);
    }

}

