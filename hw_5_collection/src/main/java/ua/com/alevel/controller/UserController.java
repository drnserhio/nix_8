package ua.com.alevel.controller;

import ua.com.alevel.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ua.com.alevel.StringerUtil.*;

public class UserController {

    private UserService service;

    public UserController() {
        this.service = new UserService();
    }

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void choose(String in, BufferedReader reader) throws Exception {

        switch (in) {
            case "1":
                createSetMenu(reader);
                break;
            case "2":
                addNumber(reader);
                break;
            case "3":
                addVarargsNumber(reader);
                break;
            case "4":
                joinWithMathSet(reader);
                break;
            case "5":
                joinWithVarArgsMathSet(reader);
                break;
            case "6":
                interSectionWithMathSet(reader);
                break;
            case "7":
                interSectionWithVarargsMathSet(reader);
                break;
            case "8":
                sortDesc();
                break;
            case "9":
                sortDescwithIndex(reader);
                break;
            case "10":
                sortDescWithNumber(reader);
                break;
            case "11":
                sortAsc();
                break;
            case "12":
                sortAscwithIndex(reader);
                break;
            case "13":
                sortAscWithNumber(reader);
                break;
            case "14":
                getValue(reader);
                break;
            case "15":
                getMax();
                break;
            case "16":
                getMin();
                break;
            case "17":
                getAverage();
                break;
            case "18":
                getMedian();
                break;
            case "19":
                toArray();
                break;
            case "20":
                toArrayWithIndex(reader);
                break;
            case "21":
                cutArray(reader);
                break;
            case "22":
                clear();
                break;
            case "23":
                clearWithArrayNumber(reader);
                break;
            case "24":
                showSet();
                break;
            case "25":
                System.exit(0);
                break;
            default:
                exception("Empty wrong...");
        }
        menu();
    }

    private void showSet() {
        service.showSet();
    }

    private void clearWithArrayNumber(BufferedReader reader) {
        service.clearWithArrayNumber(reader);
    }

    private void clear() {
        service.clear();
    }

    private void cutArray(BufferedReader reader) {
        service.cutArray(reader);
    }

    private void toArrayWithIndex(BufferedReader reader) {
        service.toArrayWithIndex(reader);
    }

    private void toArray() {
        service.toArray();
    }

    private void getMedian() {
        service.getMedian();
    }

    private void getAverage() {
        service.getAverage();
    }

    private void getMin() {
        service.getMin();
    }

    private void getMax() {
        service.getMax();
    }

    private void getValue(BufferedReader reader) {
        service.getValue(reader);
    }

    private void sortAscWithNumber(BufferedReader reader) {
        service.sortAscWithNumber(reader);
    }

    private void sortAscwithIndex(BufferedReader reader) {
        service.sortAscwithIndex(reader);
    }

    private void sortAsc() {
        service.sortAsc();
    }

    private void sortDescWithNumber(BufferedReader reader) {
        service.sortDescWithNumber(reader);
    }

    private void sortDescwithIndex(BufferedReader reader) {
        service.sortDescwithIndex(reader);
    }

    private void sortDesc() {
        service.sortDesc();
    }

    private void interSectionWithVarargsMathSet(BufferedReader reader) {
        service.interSectionWithVarargsMathSet(reader);
    }

    private void interSectionWithMathSet(BufferedReader reader) {
        service.interSectionWithMathSet(reader);
    }

    private void joinWithVarArgsMathSet(BufferedReader reader) {
        service.joinWithVarArgsMathSet(reader);
    }

    private void joinWithMathSet(BufferedReader reader) {
        service.joinWithMathSet(reader);
    }

    private void addVarargsNumber(BufferedReader reader) {
        service.addVarargsNumber(reader);
    }

    private void addNumber(BufferedReader reader) {
        service.addNumber(reader);
    }

    private void createSetMenu(BufferedReader reader) throws Exception {
        service.createSetMenu(reader);
    }

    private void menu() {
        System.out.println(
                "\n\t\t| If you entry : |\n" +
                        "--------------------------------------------\n" +
                        " 1 - Create Set Menu\n" +
                        " 2 - Add Number\n" +
                        " 3 - Add varargs Number -> (example \'1 2 3 4 5 6\' )\n" +
                        " 4 - Join with MathSet\n" +
                        " 5 - Join with varargs MathSet\n" +
                        " 6 - InterSection with MathSet \n" +
                        " 7 - InterSection with varargs MathSet \n" +
                        "--------------------------------------------\n" +
                        " 8 - Sort Desc \n" +
                        " 9 - Sort Desc with (start -> end) index\n" +
                        " 10 - Sort Desc with Number\n" +
                        " 11 - Sord Asc \n" +
                        " 12 - Sord Asc with (start -> end) index\n" +
                        " 13 - Sord Asc with Number\n" +
                        "--------------------------------------------\n" +
                        " 14 - Get value for -> [index]\n" +
                        " 15 - Get max value\n" +
                        " 16 - Get min value\n" +
                        " 17 - Get average\n" +
                        " 18 - Get median\n" +
                        "--------------------------------------------\n" +
                        " 19 - Number toArray\n" +
                        " 20 - Number toArray with (start -> end) index\n" +
                        " 21 - Cut array with (start -> end) index\n" +
                        " 22 - Clear\n" +
                        " 23 - Clear array Number (example \'1 2 3 4 5 6\' )\n" +
                        " 24 - Show Set\n" +
                        " 25 - Exit\n" +
                        "--------------------------------------------"
        );
    }
}
