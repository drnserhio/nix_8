package com.ua.alevel.gamefirst.triangle;

public final class Triangle {

    private Triangle() {}

    public static String area(Pointer a, Pointer b, Pointer c) {
       double res = Math.abs((a.getX()-c.getX())*(b.getY()-a.getY())-
                (a.getX()-b.getX())*(c.getY()-a.getY()))*0.5;
        return res <= 0 ? "Not find area with this cordinats" : String.valueOf(res);
    }

}
