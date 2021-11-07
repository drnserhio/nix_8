package com.ua.alevel.controller;

import com.ua.alevel.service.ServiceApplication;
import ua.com.alevel.StringerUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    public void run() {
        menu();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String in = "";
        try {
            while ((in = reader.readLine()) != null) {
                choose(in, reader);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void menu() {
        System.out.println(

                "\n\t\t| If you entry : |\n" +
                        " 1 - Unique symbol in array [numeric]\n" +
                        " 2 - Game step Hourse\n" +
                        " 3 - Find square in Points\n" +
                        " 4 - Verification str about brackets\n" +
                        " 5 - Find count depth in Tree\n" +
                        " 6 - Game of Life\n" +
                        " 7 - Exit"
        );
    }

    private void choose(String in, BufferedReader reader) throws IOException {

        switch (in) {

            case "1":
                ServiceApplication.uniqueSymbolInArray(reader);
                break;
            case "2":
                ServiceApplication.gameStepHourse(reader);
                break;
            case "3":
                ServiceApplication.squarePoint(reader);
                break;
            case "4":
                ServiceApplication.valideBracketsInStr(reader);
                break;
            case "5":
                ServiceApplication.findCountDepthInTree(reader);
                break;
            case "6":
                ServiceApplication.gameLife(reader);
                break;
            case "7":
                System.exit(0);
                break;
            default:
                StringerUtil.exception("Empty wrong...");
        }
        menu();
    }
}
