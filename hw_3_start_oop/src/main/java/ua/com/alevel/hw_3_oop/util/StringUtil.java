package ua.com.alevel.hw_3_oop.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class StringUtil {

    private StringUtil() {
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

}
