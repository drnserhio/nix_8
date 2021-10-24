package ua.com.alevel.classes;


import ua.com.alevel.StringUtil.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ControllerBaseOp {


    public static void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        menu();
        String event;

        try {
            while ((event = reader.readLine()) != null) {
                switch (event) {

                    case "1":
                        System.out.println("Entry a string : to get the sum of numbers in it:");
                        result(String.valueOf(StringUtil.sum(reader)));
                        menu();
                        break;
                    case "2":
                        System.out.println("Entry string : to count the number of characters in it:");
                        result(StringUtil.sortAlphabet(reader));
                        menu();
                        break;
                    case "3":
                        System.out.println("Entry number study lesson : to determine when this lesson end:");
                        result(TimeLesson.findEndLesson(reader));
                        menu();
                        break;
                    case "4":
                        System.exit(0);
                        break;
                    default:
                        result("Error entry");
                        menu();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void menu() {
        System.out.println(
                "\t\t| If you entry : |\n" +
                        " 1 - Sum digit in str\n" +
                        " 2 - Count char in str \n" +
                        " 3 - End lesson time \n" +
                        " 4 - Exit"
        );
    }

    private static void result(String str) {
        System.out.println("___________________________________\n"
                + "You result: " + "\n" + str + "\n"
                + "___________________________________");
    }
}
