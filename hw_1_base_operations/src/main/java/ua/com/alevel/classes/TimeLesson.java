package ua.com.alevel.classes;


import ua.com.alevel.classes.util.StringerUtil;

import java.io.BufferedReader;
import java.io.IOException;

public class TimeLesson {

    private static final int LESSON_DURATION = 45;
    private static final int VARIABLE_UNUVEN = 5;
    private static final int VARIABLE_EVEN = 15;

    public static String findEndLesson(BufferedReader reader) throws IOException {
        int i = 0;
        try {
            i = Integer.parseInt(reader.readLine());
            if (i > 10) {
                throw new IndexOutOfBoundsException();
            }

        } catch (IndexOutOfBoundsException e) {
            return StringerUtil.exceptionString(e.getClass().getName());
        } catch (Exception e) {
            return StringerUtil.exceptionString(e.getClass().getName());
        }

        int res = i * LESSON_DURATION + (i / 2) * VARIABLE_UNUVEN + (((i + 1) / 2) - 1) * VARIABLE_EVEN;

        return (res / 60 + 9) + " " + (res % 60);

    }


}
