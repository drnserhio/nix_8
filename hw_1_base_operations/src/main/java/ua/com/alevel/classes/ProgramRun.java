package ua.com.alevel.classes;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgramRun {


    public static void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("If you entry \n" +
                            " 1 - Sum digit in str\n" +
                            " 2 - Count char in str \n" +
                            " 3 - End lesson time \n" +
                            " 4 - Exit\n");

        System.out.println("Select you event ");

        String event;

        try {
            while ((event = reader.readLine()) != null) {
                switch (event) {

                    case "1" :
                        System.out.println("Entry a string : to get the sum of numbers in it");
                        System.out.println(StringeSumDigit.sum(reader));
                    break;
                    case "2" :
                        System.out.println("Entry string : to count the number of characters in it");
                        System.out.println(StringeCharCount.sortAlphabet(reader));
                    break;
                    case "3" :
                        System.out.println("Entry number study lesson : to determine when this lesson end");
                        System.out.println(TimeLesson.findEndLesson(reader));
                    case "4" :
                        System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
