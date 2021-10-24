package ua.com.alevel.hw_2_str.classes;


import ua.com.alevel.reversestring.StringerUtil;

import java.io.BufferedReader;
import java.io.IOException;


public class AdapterStringerReverse {

    public static String reverse(BufferedReader reader) throws IOException {
        String str = null;
        try {
            str = reader.readLine();
            if (StringerUtil.strIsEmpty(str)) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            return StringerUtil.exceptionString(e.getClass().getName());
        }
        return StringerUtil.reverse(str);
    }

    public static String reverseSub(BufferedReader reader) throws IOException {
        String str = null;
        String desk = null;
        try {
            str = reader.readLine();

            System.out.println("Entry desk :");
            desk = reader.readLine();

            if (StringerUtil.strIsEmpty(desk) ||
                    StringerUtil.strIsEmpty(str)) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            return StringerUtil.exceptionString(e.getClass().getName());
        }
        return StringerUtil.reverse(str, desk);
    }

    public static String reverseStrIndex(BufferedReader reader) throws IOException {
        String str = null;
        int firstIndex = 0;
        int lastIndex = 0;

        try {
            str = reader.readLine();

            System.out.println("Entry start index :");
            firstIndex = Integer.parseInt(reader.readLine());

            System.out.println("Entry end index :");
            lastIndex = Integer.parseInt(reader.readLine());

        } catch (NullPointerException e) {
            return StringerUtil.exceptionString(e.getClass().getName());
        } catch (NumberFormatException e) {
            return StringerUtil.exceptionString(e.getClass().getName());
        }

        return StringerUtil.reverse(str, firstIndex, lastIndex);
    }

}
