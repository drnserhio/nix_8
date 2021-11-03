package com.ua.alevel.gamefirst.triangle;

import java.awt.*;

public final class Triangle {

    private Triangle() {}

    public static double area(Point a, Point b, Point c) {
        return Math.abs((a.getX()-c.getX())*(b.getY()-a.getY())-
                (a.getX()-b.getX())*(c.getY()-a.getY()))*0.5;
    }

}
