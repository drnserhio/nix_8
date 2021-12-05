package ua.com.alevel;

import ua.com.alevel.service.CalendarService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ua.com.alevel.StringerUtil.print;

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
            case "4":
                calendarService.plusMonth(reader);
                calendarService.getTime();
                break;
            case "5":
                calendarService.plusYears(reader);
                calendarService.getTime();
                break;
            case "6":
                calendarService.minusYears(reader);
                calendarService.getTime();
                break;

            case "7":
                calendarService.minusDays(reader);
                calendarService.getTime();
                break;
            case "8":
                calendarService.minusMonth(reader);
                calendarService.getTime();
                //-----------------------------
                break;
            case "9":
                calendarService.plusHours(reader);
                calendarService.getTime();
                break;
            case "10":
                calendarService.plusMinutes(reader);
                calendarService.getTime();
                break;
            case "11":
                calendarService.plusSeconds(reader);
                calendarService.getTime();
                break;
            case "12":
                calendarService.plusMilliseconds(reader);
                calendarService.getTime();
                break;
            case "13":
                calendarService.minusHours(reader);
                calendarService.getTime();
                break;
            case "14":
                calendarService.minusMinutes(reader);
                calendarService.getTime();
                break;
            case "15":
                calendarService.minusSeconds(reader);
                calendarService.getTime();
                break;
            case "16":
                calendarService.minusMilliseconds(reader);
                calendarService.getTime();
                break;
            case "17":
                calendarService.getTime();
                break;
            case "18":
                calendarService.getTimeJavaStyle();
                break;
            case "19":
                System.exit(0);
            default:
                print("Entry wrong...");
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
                        " 5 - Plus years\n" +
                        " 6 - Minus years\n" +
                        " 7 - Minus days\n" +
                        " 8 - Minus month \n" +
                        "--------------------------------------------\n" +
                        " 9 - Plus hours \n" +
                        "10 - Plus minutes\n" +
                        "11 - Plus Seconds\n" +
                        "12 - Plus milliSeconds \n" +
                        "13 - Minus hours\n" +
                        "14 - Minus minutes\n" +
                        "15 - Minus seconds\n" +
                        "16 - Minus milliseconds\n" +
                        "--------------------------------------------\n" +
                        "17 - Get DataTime\n" +
                        "18 - Get DateTime JavaStyle\n" +
                        "19 - Exit\n" +
                        "--------------------------------------------"
        );
    }


}
