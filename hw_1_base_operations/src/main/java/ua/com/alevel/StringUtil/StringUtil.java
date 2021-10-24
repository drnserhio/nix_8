package ua.com.alevel.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public final class StringUtil {

    private StringUtil() {}

    public static String sortAlphabet(BufferedReader reader) throws IOException {
        String out = reader.readLine();
        int number = 0;

        try {
            number = Integer.parseInt(out);
        } catch (NumberFormatException e) {
        }

        if (out.isEmpty() || out.length() < 1 || number != 0) {
            return "Error entry...";
        } else {
            return changeAlphabet(out);
        }
    }

    private static String changeAlphabet(String str) {
        List<String> alphabet = Arrays.stream(str.replaceAll("[^a-zA-Zа-яёА-ЯЁ]", "")
                        .toLowerCase().split(""))
                .collect(Collectors.toList());

        Map<String, Integer> symbol = new TreeMap<>();
        for (String s : alphabet) {
            symbol.put(s, Collections.frequency(alphabet, s));
        }
        return symbol.toString().replaceAll(" ", "\n").replaceAll("[{},]", "");
    }

    public static long sum(BufferedReader reader) throws IOException {

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
}
