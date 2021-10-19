package ua.com.alevel.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class StringeCharCount {

    public static String sortAlphabet(BufferedReader reader) throws IOException {

        String out = reader.readLine();

        if (out.isEmpty() || out.length() <= 1) {
            return "Error entry empty str...";
        } else {
            return changeAlphabet(out);
        }
    }

    private static String changeAlphabet(String str) {
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
