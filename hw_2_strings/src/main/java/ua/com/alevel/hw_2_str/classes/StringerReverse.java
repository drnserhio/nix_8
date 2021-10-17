package ua.com.alevel.hw_2_str.classes;

import java.util.StringJoiner;

public class StringerReverse {

    public static String reverse(String str) {
        String out = "";
        StringJoiner stringJoiner = new StringJoiner(" ");
        
        String w[] = str.split(" ");

        for (int i = 0; i < w.length; i++) {
            stringJoiner.add(swapStr(w[i]));
        }
        return stringJoiner.toString();
    }

    public static String reverse(String str, String desk) {
        return str.replaceFirst(desk, "*").replaceAll("\\*", swapStr(desk));
    }

    public static String reverse(String str, int firstIndex, int lastIndex) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        String f = str.substring(0,firstIndex);
        String last  = str.substring(lastIndex+1);
        String[] out = str.substring(firstIndex,lastIndex+1).split(" ");

        for (int i = 0; i < out.length; i++) {
            stringJoiner.add(swapStr(out[i]));
        }
       return f.concat(stringJoiner.toString().concat(last));
    }


   private static String swapStr(String str) {
        String out = "";
        char[] arr = str.toCharArray();
        for (int i = arr.length-1; i >=0 ; i--) {
            out = out.concat(String.valueOf(arr[i]));
        }
        return out;
    }

}
