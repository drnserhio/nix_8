package ua.com.alevel.classes;

import java.io.BufferedReader;
import java.io.IOException;

public class StringeSumDigit {

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
