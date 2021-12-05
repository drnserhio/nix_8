package ua.com.alevel;

import ua.com.alevel.service.CalendarService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalendarController {

    private CalendarService calendarService = new CalendarService();


    public void run() {
        menu();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String in = "";
        try {
            while ((in = reader.readLine()) != null) {
                choose(in, reader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void choose(String in, BufferedReader reader) throws Exception {
        switch (in) {
            case "1":
                calendarService.createPattern(reader);
            break;
            case "2":
                calendarService.createCalendar(reader);
            break;
            case "3":
                calendarService.plusDays(reader);
                calendarService.getTime();
                break;
            case "16":
                calendarService.getTime();
            break;
            default:
                System.out.println("wrong..");
        }
        menu();
    }
    private void menu() {
        System.out.println(
                "\n\t\t| If you entry : |\n" +
                        "--------------------------------------------\n" +
                        " 1 - Choose Calendar pattern - Menu\n" +
                        " 2 - Create Calendar\n" +
                        "--------------------------------------------\n" +
                        " 3 - Plus days\n" +
                        " 4 - Plus month\n" +
                        " 4 - Plus years\n" +
                        " 5 - Minus years\n" +
                        " 6 - Minus days\n" +
                        " 7 - Minus month \n" +
                        "--------------------------------------------\n" +
                        " 8 - Plus hours \n" +
                        " 9 - Plus minutes\n" +
                        "10 - Plus Seconds\n" +
                        "11 - Plus milliSeconds \n" +
                        "12 - Minus hours\n" +
                        "13 - Minus minutes\n" +
                        "14 - Minus seconds\n" +
                        "15 - Minus milliseconds\n" +
                        "--------------------------------------------\n" +
                        "16 - Get DataTime\n" +
                        "17 - Exit\n" +
                        "--------------------------------------------"
        );
    }


}
