package com.ua.alevel.gamefirst.uniq;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public final class UniqueSymbol {

    private UniqueSymbol(){}

    public static int countUnicSymbol(String arr) {
       Set<String> list = Arrays.asList(arr.split(" ")).stream()
               .filter(s -> s.matches("\\d"))
                .collect(Collectors.toSet());
       return list.size();
    }
}
