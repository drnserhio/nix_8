package ua.com.alevel.service;

import ua.com.alevel.resources.impl.FormatCalendar;

import java.io.BufferedReader;
import java.io.IOException;

import static ua.com.alevel.constant.CalendarPatterConst.*;

public class FormatCalendarService {

    private FormatCalendar formatter = new FormatCalendar();
    private String PATTERN;

    private void menu() {
        System.out.println(
                "\n\t\t| Menu pattern : |\n" +
                        "--------------------------------------------\n" +
                        " 1 - Format -> dd/mm/yyyy \n" +
                        " 2 - Format -> m/d/yyyy\n" +
                        " 3 - Format -> mmm-d-yyyy\n" +
                        " 4 - Format -> dd-mmm-yyyy\n" +
                        " 5 - Exit\n" +
                        "--------------------------------------------"
        );
    }


    public String chooseFormat(BufferedReader reader) throws IOException {
        menu();
        switch (reader.readLine()) {

            case "1":
                PATTERN = DOUBLE_SYMBOL_DAY_AND_MONTH_YEAR;
                break;
            case "2":
                PATTERN = SIMPLE_SYMBOL_MONTH_AND_DAY_YEAR;
                break;
            case "3":
                PATTERN = THREE_SYMBOL_MONTH_AND_ONE_SYMBOL_DAY_YEAR;
                break;
            case "4":
                PATTERN = DOUBLE_SYMBOL_DAY_AND_THREE_SYMBOL_DAY_YEAR;
                break;
        }
        return PATTERN;
        }

}
