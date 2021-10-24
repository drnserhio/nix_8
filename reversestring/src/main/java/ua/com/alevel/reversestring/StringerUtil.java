package ua.com.alevel.reversestring;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public final class StringerUtil {

    private StringerUtil() {
    }

    public static String reverse(String str) {

        StringBuilder stringBuilder = new StringBuilder();
        String w[] = str.split(" ");

        for (int i = 0; i < w.length; i++) {
            stringBuilder.append(swapStr(w[i])).append(" ");
        }
        return stringBuilder.toString();
    }

    public static String reverse(String str, String desk) {
        return str.replaceFirst(desk, "*").replaceAll("\\*", swapStr(desk));
    }

    public static String reverse(String str, int firstIndex, int lastIndex) {
        if (strIsEmpty(str)) {
            return exceptionString(new NullPointerException().getClass().getName());
        }
        if (firstIndex > str.length()
                || lastIndex > str.length()
                || firstIndex > lastIndex) {
            return exceptionString(new IndexOutOfBoundsException().getClass().getName());
        }
        StringBuilder stringBuilder = new StringBuilder();
        String first = str.substring(0, firstIndex);
        String last = str.substring(lastIndex + 1);
        String[] out = str.substring(firstIndex, lastIndex + 1).split(" ");

        for (int i = 0; i < out.length; i++) {
            stringBuilder.append(swapStr(out[i])).append(" ");
        }
        return first.concat(stringBuilder.toString().concat(last));
    }


    private static String swapStr(String str) {
        String out = "";
        char[] arr = str.toCharArray();
        for (int i = arr.length - 1; i >= 0; i--) {
            out = out.concat(String.valueOf(arr[i]));
        }
        return out;
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
