package ua.com.alevel.classes;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgramRun {
    public static final String INFO_MENU = "If you entry \n" +
            " 1 - Sum digit in str\n" +
            " 2 - Count char in str \n" +
            " 3 - End lesson time \n" +
            " 4 - Exit";

    public static final String INDENT = "--------------------";

    public static final String SUCCESS = "Your result: \n";
    public static final String EVENT = "Select you event :";


    public static void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(INFO_MENU);

        System.out.println(EVENT);

        String event;

        try {
            while ((event = reader.readLine()) != null) {
                switch (event) {

                    case "1":
                        System.out.println("Entry a string : to get the sum of numbers in it:");
                        System.out.println(INDENT + "\n" + SUCCESS + StringeSumDigit.sum(reader) + "\n" + INDENT + "\n");
                        System.out.println(INFO_MENU + "\n" + EVENT);
                        break;
                    case "2":
                        System.out.println("Entry string : to count the number of characters in it:");
                        System.out.println(INDENT + "\n" + SUCCESS + StringeCharCount.sortAlphabet(reader) + "\n" + INDENT + "\n");
                        System.out.println(INFO_MENU + "\n" + EVENT);
                        break;
                    case "3":
                        System.out.println("Entry number study lesson : to determine when this lesson end:");
                        System.out.println(INDENT + "\n" + SUCCESS + TimeLesson.findEndLesson(reader) + "\n" + INDENT + "\n");
                        System.out.println(INFO_MENU + "\n" + EVENT);
                        break;
                    case "4":
                        System.exit(0);
                        break;
                    default:
                        System.out.println(INDENT + "\n" + SUCCESS +"Error entry"  + "\n" + INDENT + "\n" + INFO_MENU);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
