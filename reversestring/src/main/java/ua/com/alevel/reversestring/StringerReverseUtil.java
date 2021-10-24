package ua.com.alevel.reversestring;

import java.util.StringJoiner;

public class StringerReverseUtil {

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
        if (str.isEmpty()
                || str.length() < 1) {
            return "Exception : " +  new NullPointerException();
        }
        if (firstIndex > str.length()
                || lastIndex > str.length()
                    || firstIndex > lastIndex) {
            return  "Exception : "  + new IndexOutOfBoundsException();
        }
        StringBuilder stringBuilder = new StringBuilder();
        String f = str.substring(0, firstIndex);
        String last = str.substring(lastIndex + 1);
        String[] out = str.substring(firstIndex, lastIndex + 1).split(" ");
        for (int i = 0; i < out.length; i++) {
            stringBuilder.append(swapStr(out[i])).append(" ");
        }
        return f.concat(stringBuilder.toString().concat(last));
    }

    private static String swapStr(String str) {
        String out = "";
        char[] arr = str.toCharArray();
        for (int i = arr.length - 1; i >= 0; i--) {
            out = out.concat(String.valueOf(arr[i]));
        }
        return out;
    }
}
