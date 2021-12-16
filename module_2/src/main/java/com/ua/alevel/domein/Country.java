package com.ua.alevel.domein;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    private String nameCountry;
    private int countNeighbors;
    private HashMap<String, Integer> neighborWithCost;
}
