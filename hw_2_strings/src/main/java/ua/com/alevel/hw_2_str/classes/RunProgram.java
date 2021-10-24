package ua.com.alevel.hw_2_str.classes;

import ua.com.alevel.hw_2_str.classes.AdapterStringerReverse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunProgram {

    public static final String INFO_MENU = "\t\t| If you entry : |\n" +
            " 1 - Reverse you str\n" +
            " 2 - Reverse substring in you string \n" +
            " 3 - Reverse substring in you string between (start index, last index)  \n" +
            " 4 - Exit";
    public static final String INDENT = "--------------------";
    public static final String SUCCESS = "Your result: \n";
    public static final String EVENT = "\t\t| Select you event : |";

    public static void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(INFO_MENU);
        System.out.println(EVENT);
        String event;
        try {
            while ((event = reader.readLine()) != null) {
                switch (event) {

                    case "1":
                        System.out.println("Entry string :");
                        System.out.println(INDENT + "\n" + SUCCESS + AdapterStringerReverse.reverse(reader) + "\n" + INDENT + "\n");
                        System.out.println(INFO_MENU + "\n" + EVENT);

                        break;
                    case "2":
                        System.out.println("Entry string :");
                        System.out.println(INDENT + "\n" + SUCCESS + AdapterStringerReverse.reverseSub(reader) + "\n" + INDENT + "\n");
                        System.out.println(INFO_MENU + "\n" + EVENT);
                        break;
                    case "3":
                        System.out.println("Entry string :");
                        System.out.println(INDENT + "\n" + SUCCESS + AdapterStringerReverse.reverseStrIndex(reader) + "\n" + INDENT + "\n");
                        System.out.println(INFO_MENU + "\n" + EVENT);
                        break;
                    case "4":
                        System.exit(0);
                        break;
                    default:
                        System.out.println(INDENT + "\n" + SUCCESS +"Error entry"  + "\n" + INDENT + "\n");
                        System.out.println(INFO_MENU + "\n" + EVENT);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
