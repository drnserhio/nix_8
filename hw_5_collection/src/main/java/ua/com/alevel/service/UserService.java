package ua.com.alevel.service;

import org.apache.commons.lang3.math.NumberUtils;
import ua.com.alevel.MathSetImpl;
import ua.com.alevel.controller.MathSetDoubleConroller;
import ua.com.alevel.controller.MathSetFloatConroller;
import ua.com.alevel.controller.MathSetIntegerConroller;
import ua.com.alevel.controller.MathSetLongConroller;
import ua.com.alevel.exception.EmptyArrayException;
import ua.com.alevel.exception.NotNullException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

import static ua.com.alevel.StringerUtil.*;
import static ua.com.alevel.constant.ExceptionConstant.*;

public class UserService {

    private MathSetImpl mathSet;


    public void clearWithArrayNumber(BufferedReader reader) {
        String n = "";
        try {

            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void clear() {
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            mathSet.clear();
            mathSet = null;
        } catch (EmptyArrayException e) {
            printWarn(CREATE_SET);
        }
    }

    public void cutArray(BufferedReader reader) {
        String start = "";
        String end = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            print("Entry start index: ");
            start = reader.readLine();
            if (strIsEmpty(start)) {
                throw new NullPointerException();
            }
            print("Entry end index :");
            end = reader.readLine();
            if (strIsEmpty(end)) {
                throw new NullPointerException();
            }
            this.mathSet = mathSet.cut(Integer.parseInt(start), Integer.parseInt(end));
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + " start index: " + start + ", " + "end index: " + end);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        } catch (IOException e) {}

    }

    public void toArrayWithIndex(BufferedReader reader) {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void toArray() {
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            result(Arrays.toString(mathSet.toArray()));
        } catch (EmptyArrayException e) {
            printWarn(CREATE_SET);
        }
    }

    public void getMedian() {
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            result(String.valueOf(mathSet.getMedian()));
        } catch (EmptyArrayException e) {
            printWarn(CREATE_SET);
        }
    }

    public void getAverage() {
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            result(String.valueOf(mathSet.getAverage()));
        } catch (EmptyArrayException e) {
            printWarn(CREATE_SET);
        }
    }

    public void getMin() {
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            result(String.valueOf(mathSet.getMin()));
        } catch (EmptyArrayException e) {
            printWarn(CREATE_SET);
        }
    }

    public void getMax() {
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            result(String.valueOf(mathSet.getMax()));
        } catch (EmptyArrayException e) {
            printWarn(CREATE_SET);
        }
    }

    public void getValue(BufferedReader reader) {
        print("Entry index: ");
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            n = reader.readLine();
            if (strIsEmpty(n)) {
                throw new NullPointerException(EMPTY_STR + n);
            }
            result(String.valueOf(mathSet.get(Integer.parseInt(n))));
        } catch (EmptyArrayException | IOException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(IS_NOT_NUMBER + n);
        } catch (IndexOutOfBoundsException e) {
            printWarn(OUT_INDEX_ARRAY);
        }

    }

    public void sortAscWithNumber(BufferedReader reader) {
        print("Entry number: ");
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            mathSet.sortAsc(NumberUtils.createNumber(reader.readLine()));
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(IS_NOT_NUMBER);
        } catch (IndexOutOfBoundsException e) {
            printWarn(NOT_NUMBER_IN_SET);
        } catch (IOException e) {
        }
    }

    public void sortAscwithIndex(BufferedReader reader) {
        String start = "";
        String end = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            print("Entry start index: ");
            start = reader.readLine();
            if (strIsEmpty(start)) {
                throw new NullPointerException();
            }
            print("Entry end index :");
            end = reader.readLine();
            if (strIsEmpty(end)) {
                throw new NullPointerException();
            }
            mathSet.sortAsc(Integer.parseInt(start), Integer.parseInt(end));
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + " start index: " + start + ", " + "end index: " + end);
        } catch (NumberFormatException e) {
            printWarn(IS_NOT_NUMBER);
        } catch (IndexOutOfBoundsException e) {
            printWarn(OUT_INDEX_ARRAY);
        } catch (IOException e) {
        }
    }

    public void sortAsc() {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            mathSet.sortAsc();
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        }
    }

    public void sortDescWithNumber(BufferedReader reader) {
        print("Entry number: ");
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            mathSet.sortDesc(NumberUtils.createNumber(reader.readLine()));
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(IS_NOT_NUMBER);
        } catch (IndexOutOfBoundsException e) {
            printWarn(NOT_NUMBER_IN_SET);
        } catch (IOException e) {
        }
    }

    public void sortDescwithIndex(BufferedReader reader) {
        String start = "";
        String end = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            print("Entry start index: ");
            start = reader.readLine();
            if (strIsEmpty(start)) {
                throw new NullPointerException();
            }
            print("Entry end index :");
            end = reader.readLine();
            if (strIsEmpty(end)) {
                throw new NullPointerException();
            }
            mathSet.sortDesc(Integer.parseInt(start), Integer.parseInt(end));
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + " start index: " + start + ", " + "end index: " + end);
        } catch (NumberFormatException e) {
            printWarn(IS_NOT_NUMBER);
        } catch (IndexOutOfBoundsException e) {
            printWarn(OUT_INDEX_ARRAY);
        } catch (IOException e) {
        }
    }

    public void sortDesc() {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            mathSet.sortDesc();
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        }
    }

    public void interSectionWithVarargsMathSet(BufferedReader reader) {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void interSectionWithMathSet(BufferedReader reader) {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void joinWithVarArgsMathSet(BufferedReader reader) {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void joinWithMathSet(BufferedReader reader) {
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(e.getMessage());
        }
    }

    public void addVarargsNumber(BufferedReader reader) {
        print("Entry var args number: ");
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }

            n = reader.readLine();
            String[] arrNumber = n.split(" ");
            for (int i = 0; i < arrNumber.length; i++) {
                mathSet.add(NumberUtils.createNumber(arrNumber[i]));
            }
        } catch (EmptyArrayException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(IS_NOT_NUMBER);
        } catch (IOException e) {}
    }

    public void addNumber(BufferedReader reader) {
        print("Entry number: ");
        String n = "";
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            n = reader.readLine();
            if (strIsEmpty(n)) {
                throw new NullPointerException();
            }
            mathSet.add(NumberUtils.createNumber(n));
        } catch (EmptyArrayException | IOException e) {
            printWarn(EMPTY_ARRAY);
        } catch (NullPointerException e) {
            printWarn(EMPTY_STR + n);
        } catch (NumberFormatException e) {
            printWarn(IS_NOT_NUMBER);
        }
    }

    public void createSetMenu(BufferedReader reader) throws Exception {
        try {
            if (mathSet != null) {
                throw new NotNullException("");
            }
            if (mathSet == null) {
                this.mathSet = createSet(reader);
                result(mathSet.toString());
            }

        } catch (NullPointerException e) {
            printWarn("Error create set , because set is null");
        } catch (NotNullException e) {
            printWarn(ERROR_CREATE_NEW_SET + mathSet.size());
            printWarn(CLEAR_SET);
        }
    }

    public void showSet() {
        try {
            if (mathSet == null ||
                    mathSet.getArrayMath().length < 1) {
                throw new EmptyArrayException(CREATE_SET);
            }
            mathSet.toPrint();
        } catch (EmptyArrayException e) {
            printWarn(CREATE_SET);
        }

    }

    public static void printWarn(String str) {
        System.out.println("_____________________________________________\n" +
                "        *********Warning********\n"
                + " |||||----" + str + "----|||||\n"
                + "_____________________________________________");
    }

    private void menu() {
        System.out.println(

                "\n\t\t| Menu create random MathSet, choose type:  |\n" +
                        "------------------------\n" +
                        " 1 - MathSet<Integer> \n" +
                        " 2 - MathSet<Long>\n" +
                        " 3 - MathSet<Double>\n" +
                        " 4 - MathSet<Float>\n" +
                        "------------------------"
        );
    }

    public MathSetImpl<? extends Number> createSet(BufferedReader reader) throws Exception {
        menu();
        MathSetImpl mathSet = null;
        switch (reader.readLine()) {

            case "1":
                mathSet = createSetInteger(reader);
                break;
            case "2":
                mathSet = createSetLong(reader);
                break;
            case "3":
                mathSet = createSetDouble(reader);
                break;
            case "4":
                mathSet = createSetFloat(reader);
                break;
        }
        return mathSet;
    }

    private MathSetImpl createSetInteger(BufferedReader reader)
            throws Exception {
        return new MathSetIntegerConroller().createSet(reader);
    }

    private MathSetImpl createSetLong(BufferedReader reader)
            throws Exception {
        return new MathSetLongConroller().createSet(reader);
    }

    private MathSetImpl createSetDouble(BufferedReader reader)
            throws Exception {
        return new MathSetDoubleConroller().createSet(reader);
    }

    private MathSetImpl createSetFloat(BufferedReader reader)
            throws Exception {
        return new MathSetFloatConroller().createSet(reader);
    }


}
