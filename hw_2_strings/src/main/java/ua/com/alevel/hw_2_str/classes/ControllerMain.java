package ua.com.alevel.hw_2_str.classes;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ControllerMain {



    public static void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        menu();

        String event;

        try {
            while ((event = reader.readLine()) != null) {
                switch (event) {

                    case "1":
                        System.out.println("Entry string :");
                        result(AdapterStringerReverse.reverse(reader));
                        menu();
                        break;
                    case "2":
                        System.out.println("Entry string :");
                        result(AdapterStringerReverse.reverseSub(reader));
                        menu();
                        break;
                    case "3":
                        System.out.println("Entry string :");
                        result(AdapterStringerReverse.reverseStrIndex(reader));
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
                        " 1 - Reverse you str\n" +
                        " 2 - Reverse substring in you string \n" +
                        " 3 - Reverse substring in you string between (start index, last index)  \n" +
                        " 4 - Exit"
        );
    }

    private static void result(String str) {
        System.out.println("___________________________________\n"
                + "You result: " + "\n" + str + "\n"
                + "___________________________________");
    }
}
