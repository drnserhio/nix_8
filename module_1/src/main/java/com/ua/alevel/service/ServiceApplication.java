package com.ua.alevel.service;

import com.ua.alevel.gamefirst.hoursegame.Game;
import com.ua.alevel.gamefirst.triangle.Triangle;
import com.ua.alevel.gamefirst.uniq.UniqueSymbol;
import com.ua.alevel.gamesecond.brackets.ValidBrackets;
import com.ua.alevel.gamesecond.gamelife.GameLife;
import com.ua.alevel.gamesecond.treedepth.Tree;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static ua.com.alevel.StringerUtil.*;

public class ServiceApplication {
    public static void uniqueSymbolInArray(BufferedReader reader) throws IOException {
        System.out.println("Entry array:  \n" +
                "(example: | 1 2 3 4 5 |)");
        String in = reader.readLine();

        try {
            String[] validate = in.split(" ");

            if (strIsEmpty(in) || validate.length < 1) {
                throw new NullPointerException();
            }
            Set<String> list = Arrays.stream(validate)
                    .map(s -> s.replaceAll("\\d", ""))
                    .collect(Collectors.toSet());
            if (list.size() > 1) {
                throw new NumberFormatException();
            }

            result(String.valueOf(UniqueSymbol.countUnicSymbol(in)));
        } catch (NullPointerException e) {
            exception(e.getClass().getName());
        } catch (NumberFormatException e) {
            exception(e.getClass().getName());
        }

    }

    public static void gameStepHourse(BufferedReader reader) throws IOException {
        System.out.println("Entry coordinat step vertical :");
        String posX = reader.readLine();
        System.out.println("Entry coordinat step horizontal :");
        String posY = reader.readLine();

        try {
            if (strIsEmpty(posX) ||
                    strIsEmpty(posY)) {
                throw new NullPointerException();
            }

            int x = Integer.parseInt(posX);
            int y = Integer.parseInt(posY);

            gameHourse(new Game(x, y), reader);
        } catch (NullPointerException e) {
            exception(e.getClass().getName());
        } catch (NumberFormatException e) {
            exception(e.getClass().getName());
        }
    }

    private static void gameHourse(Game game, BufferedReader reader) throws IOException {
        System.out.println("Entry coordinat step vertical :");
        String n = reader.readLine();
        System.out.println("Entry coordinat step horizontal :");
        String m = reader.readLine();

        try {
            if (strIsEmpty(n) ||
                    strIsEmpty(m)) {
                throw new NullPointerException();
            }

            int x = Integer.parseInt(n);
            int y = Integer.parseInt(m);

            game.stepCoordinatHourse(x, y);
        } catch (NullPointerException e) {
            exception(e.getClass().getName());
        } catch (NumberFormatException e) {
            exception(e.getClass().getName());
        }

    }

    public static void squarePoint(BufferedReader reader) throws IOException {
        print("Entry xA coordinat: ");
        String xA = reader.readLine();
        print("Entry yA coordinat: ");
        String yA = reader.readLine();

        print("Entry xB coordinat: ");
        String xB = reader.readLine();
        print("Entry yB coordinat: ");
        String yB = reader.readLine();

        print("Entry xB coordinat: ");
        String xC = reader.readLine();
        print("Entry yB coordinat: ");
        String yC = reader.readLine();


        try {
            if (
                    strIsEmpty(xA) ||
                            strIsEmpty(yA) ||
                            strIsEmpty(xB) ||
                            strIsEmpty(yB) ||
                            strIsEmpty(xC) ||
                            strIsEmpty(yC)
            ) {
                throw new NullPointerException();
            }

            //create except numeric
            int xa = Integer.parseInt(xA);
            int ya = Integer.parseInt(yA);

            int xb = Integer.parseInt(xB);
            int yb = Integer.parseInt(yB);

            int xc = Integer.parseInt(xC);
            int yc = Integer.parseInt(yC);


            result(String.valueOf(Triangle.area(new Point(xa, ya), new Point(xb, yb), new Point(xc, yc))));
        } catch (NullPointerException e) {
            exception(e.getClass().getName());
        } catch (NumberFormatException e) {
            exception(e.getClass().getName());
        }

    }

    public static void valideBracketsInStr(BufferedReader reader) throws IOException {
        print("Entry str with brackets: ");
        String in = reader.readLine();

        try {
            if (strIsEmpty(in)) {
                throw new NullPointerException();
            }
            result("Str valid-> :" + ValidBrackets.isValidBracketsInStr(in));
        } catch (NullPointerException e) {
            exception(e.getClass().getName());
        }
    }

    public static void findCountDepthInTree(BufferedReader reader) throws IOException {
        print("Entry root element");
        String root = reader.readLine();
        print("Entry count element :");
        String count = reader.readLine();

        try {
            if (strIsEmpty(count) ||
                    strIsEmpty(root)) {
                throw new NullPointerException();
            }

            Tree.setRoot(Integer.parseInt(root));

            int counter = Integer.parseInt(count);
            for (int i = 1; i <= counter; i++) {
                print("Entry value");
                String v = reader.readLine();

                if (strIsEmpty(v)) {
                    throw new NullPointerException();
                }
                Tree.putChild(Integer.parseInt(v));
            }

            result("Max depth tree -> " + Tree.countTreeDepth());
        } catch (NullPointerException e) {
            exception(e.getClass().getName());
        } catch (NumberFormatException e) {
            exception(e.getClass().getName());
        }
    }

    public static void gameLife(BufferedReader reader) throws IOException {
        print("Size board");
        print("Entry vertical :");
        String n = reader.readLine();
        print("Entry horizontal :");
        String m = reader.readLine();

        try {
            if (strIsEmpty(n) ||
                    strIsEmpty(m)) {
                throw new NullPointerException();
            }

            //numeric
            int x = Integer.parseInt(n);
            int y = Integer.parseInt(m);

            new GameLife(x, y).startGame();

        } catch (NullPointerException e) {
            exception(e.getClass().getName());
        } catch (NumberFormatException e) {
            exception(e.getClass().getName());
        }
    }
}
