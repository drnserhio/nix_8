package com.ua.alevel.gamefirst.hoursegame;

public class Hourse {

    private String name;

    public Hourse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name ;
    }
}
