package com.ua.alevel.gamefirst.triangle;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pointer {
    
    private Double x;
    private Double y;

    public Pointer(String x, String y) {
        this.x = new Double(x);
        this.y = new Double(y);
    }
}
