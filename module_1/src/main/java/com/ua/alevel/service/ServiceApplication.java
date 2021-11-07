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

        print("Entry start coordinat");
        print("Entry coordinat x: ");
        String x1 = reader.readLine();
        print("Entry coordinat y: ");
        String y1 = reader.readLine();

        try {
            if (strIsEmpty(x1) ||
                    strIsEmpty(y1)) {
                throw new NullPointerException();
            }

            int x = Integer.parseInt(x1);
            int y = Integer.parseInt(y1);
            Game game = new Game(x, y);

            print("Entry new coordinat x: ");
            String x2 = reader.readLine();
            print("Entry new coordinat y: ");
            String y2 = reader.readLine();

            game.stepCoordinatHourse(Integer.parseInt(x1), Integer.parseInt(y2));
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



            result(Triangle.area(new Pointer(xA, yA), new Pointer(xB, yB), new Pointer(xC, yC)));
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
            addElementInTree(size, reader, tree);

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
        print("Start game?");
        print("Choose command: ****| yes or no |****");
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
