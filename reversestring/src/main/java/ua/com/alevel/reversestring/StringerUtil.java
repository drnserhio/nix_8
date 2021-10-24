package ua.com.alevel.reversestring;


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

}
