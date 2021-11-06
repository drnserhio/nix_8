package com.ua.alevel.service;

import com.ua.alevel.gamefirst.hoursegame.Game;
import com.ua.alevel.gamefirst.triangle.Pointer;
import com.ua.alevel.gamefirst.triangle.Triangle;
import com.ua.alevel.gamefirst.uniq.UniqueSymbol;
import com.ua.alevel.gamesecond.brackets.ValidBrackets;
import com.ua.alevel.gamesecond.gamelife.GameLife;
import com.ua.alevel.gamesecond.treedepth.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static ua.com.alevel.StringerUtil.*;

public class ServiceApplication {
    public static void uniqueSymbolInArray(BufferedReader reader) throws IOException {
        print("Entry array:  \n");
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
            exception(new NullPointerException().getClass().getName());
        } catch (NumberFormatException e) {
            exception(new NumberFormatException().getClass().getName());
        }

    }

    public static void gameStepHourse(BufferedReader reader) throws IOException {
        print("Entry coordinat step vertical :");
        String posX = reader.readLine();
        print("Entry coordinat step horizontal :");
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
            exception(new NullPointerException().getClass().getName());
        } catch (NumberFormatException e) {
            exception(new NumberFormatException().getClass().getName());
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
            exception(new NullPointerException().getClass().getName());
        } catch (NumberFormatException e) {
            exception(new NumberFormatException().getClass().getName());
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

        print("Entry xC coordinat: ");
        String xC = reader.readLine();
        print("Entry yC coordinat: ");
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


            result(String.valueOf(Triangle.area(new Pointer(xa, ya), new Pointer(xb, yb), new Pointer(xc, yc))));
        } catch (NullPointerException e) {
            exception(new NullPointerException().getClass().getName());
        } catch (NumberFormatException e) {
            exception(new NumberFormatException().getClass().getName());
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
            exception(new NullPointerException().getClass().getName());
        }
    }

    public static void findCountDepthInTree(BufferedReader reader) throws IOException {
        Tree tree = new Tree();
        print("Entry count element in tree");
        String count = reader.readLine();

        try {

            if (strIsEmpty(count)) {
                throw new NullPointerException();
            }
            int size = Integer.parseInt(count);
            addElementInTree(size, reader ,tree);

            result("Max depth tree -> " + Tree.countTreeDepth(tree.getRoot()));
        } catch (NullPointerException e) {
            exception(new NullPointerException().getClass().getName());
        } catch (NumberFormatException e) {
            exception(new NumberFormatException().getClass().getName());
        }
    }

    private static void addElementInTree(int size, BufferedReader reader, Tree tree) throws IOException {
        for (int i = 0; i < size; i++) {
            print("Entry element:");
            String element = reader.readLine();

            if (strIsEmpty(element)) {
                throw new NullPointerException();
            }
            tree.putChild(Integer.parseInt(element));
        }
    }

    public static void gameLife(BufferedReader reader) throws IOException {
        print("Start game?" );
        print("Choose command: ****| yes or no |****" );
        String n = reader.readLine();

        if (n.equals("no") || !n.equals("yes")) {
            return;
        }

        try {
            if (strIsEmpty(n)) {
                throw new NullPointerException();
            }

            new GameLife().go();

        } catch (NullPointerException e) {
            exception(new NullPointerException().getClass().getName());
        }
    }
}
