package ua.com.alevel.classes.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public final class StringerUtil {

    private StringerUtil() {
    }

    public static boolean strIsEmpty(String str) {
        if (str.isEmpty()
                || str.length() < 1) {
            return true;
        } else {
            return false;
        }
    }

    public static void exception(String msg) {
        System.out.println("--------------------");
        System.out.println("Exception : | " + msg + " |");
        System.out.println("--------------------");
    }

    public static String exceptionString(String msg) {
        return "Exception : | " + msg + " |";

    }

    public static long sumDigitInLines(BufferedReader reader) throws IOException {

        String str = reader.readLine();
        long sum = 0;
        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                sum += Long.parseLong(String.valueOf(chars[i]));
            }
        }
        return sum;
    }

    public static String sortAlphabet(BufferedReader reader) throws IOException {
        String out = reader.readLine();
        int number = 0;

        try {
            number = Integer.parseInt(out);
        } catch (NumberFormatException e) {
        }

        if (strIsEmpty(out) || number != 0) {
            return exceptionString("Error entry...");
        } else {
            return countAlphabetInStr(out);
        }
    }

    private static String countAlphabetInStr(String str) {
        List<String> alphabet = Arrays.stream(str.replaceAll("[^a-zA-Zа-яёА-ЯЁ]", "")
                        .toLowerCase().split(""))
                .collect(Collectors.toList());

        Map<String, Integer> symbol = new TreeMap<>();
        for (String s : alphabet) {
            symbol.put(s, Collections.frequency(alphabet, s));
        }
        return symbol.toString().replaceAll(" ", "\n").replaceAll("[{},]", "");
    }
}
