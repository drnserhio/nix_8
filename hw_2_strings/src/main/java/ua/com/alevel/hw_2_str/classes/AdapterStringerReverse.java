package ua.com.alevel.hw_2_str.classes;

import ua.com.alevel.reversestring.StringerReverseUtil;

import java.io.BufferedReader;
import java.io.IOException;

public class AdapterStringerReverse {

    public static String reverse(BufferedReader reader) throws IOException {
        return StringerReverseUtil.reverse(reader.readLine());
    }

    public static String reverseSub(BufferedReader reader) throws IOException {
        String str = reader.readLine();
        System.out.println("Entry desk :");
        String desk = reader.readLine();
        return StringerReverseUtil.reverse(str, desk);
    }

    public static String reverseStrIndex(BufferedReader reader) throws IOException {
        int firstIndex = 0;
        int lastIndex = 0;
        String str = reader.readLine();
        System.out.println("Entry start index :");
        firstIndex = Integer.parseInt(reader.readLine());
        System.out.println("Entry end index :");
        lastIndex = Integer.parseInt(reader.readLine());
        return StringerReverseUtil.reverse(str, firstIndex, lastIndex);
    }
}
