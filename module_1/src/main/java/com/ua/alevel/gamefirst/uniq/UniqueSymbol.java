package com.ua.alevel.gamefirst.uniq;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class UniqueSymbol {

    private UniqueSymbol(){}

    public static int countUnicSymbol(String arr) {
       List<Integer> list = Arrays.asList(arr.split(" ")).stream()
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());
       return Math.toIntExact(list.stream().collect(Collectors.toSet()).stream().count());
    }
}
