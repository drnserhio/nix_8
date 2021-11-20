package ua.com.alevel.util;

public final class SortArray {

    private SortArray() {}

    public static void sort(Number[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j - 1].intValue() > array[j].intValue()) {
                    Number tmp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    public static void sortDesk(Number[] array) {
        for (int i = array.length-1 ; i > 0 ; i--) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j].intValue() < array[j + 1].intValue()) {
                    Number tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    public static Number[] joinArray(Number[] first, Number[] second) {
        Number[]join = new Number[first.length + second.length];
        int count = 0;
        for(int i = 0; i<first.length; i++) {
            join[i] = first[i];
            count++;
        }
        for(int j = 0;j<second.length;j++) {
            join[count++] = second[j];
        }
       return join;
    }

    public static Number[] cutArray(Number[] original, int from, int to) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        Number[] copy = new Number[newLength];
        System.arraycopy(original, from, copy, 0,
                Math.min(original.length - from, newLength));
        return copy;
    }

    public static String toLine(Number[] a) {
        if (a == null)
            return "null";

        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(String.valueOf(a[i]));
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }
}
