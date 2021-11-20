package ua.com.alevel.controller;

import ua.com.alevel.resource.MathSetImpl;
import ua.com.alevel.util.RandomLongArray;
import java.io.BufferedReader;

public class MathSetLongConroller {

    private void menu() {
        System.out.println(

                "\n\t\t| Menu create MathSet<Long>: |\n" +
                        "------------------------\n" +
                        " 1 - Empty Constructor \n" +
                        " 2 - Constructor with capacity\n" +
                        " 3 - Constructor with array Number[]\n" +
                        " 4 - Constructor with varargs Number[]\n" +
                        " 5 - Constructor with MathSet\n" +
                        " 6 - Constructor with varargs MathSets\n" +
                        "------------------------"
        );
    }

    public MathSetImpl createSet(BufferedReader reader) throws Exception {
        menu();
        MathSetImpl mathSet = null;
        switch (reader.readLine()) {

            case "1":
                mathSet = createEmptyConstructor();
                break;
            case "2":
                mathSet = createConstructorWithCapacity(reader);
                break;
            case "3":
                mathSet = createConstructorWithArrayNumber();
                break;
            case "4":
                mathSet = createConstructorWithVargArgsNumber();
                break;
            case "5":
                mathSet = createConstructorWithMathSet();
                break;
            case "6":
                mathSet = createConstructorWithVarArgsMathSet();
                break;
        }
        return mathSet;
    }

    private MathSetImpl createConstructorWithVarArgsMathSet() throws InterruptedException {
        System.out.println("I creat VarArgsMathSet for you ;)");
        System.out.print("Paste VarArgsMathSet random in your set");
        for (int i = 0; i < 4; i++) {
            Thread.sleep(500);
            System.out.print(" .");
        }
        System.out.println("Successfull");
        return RandomLongArray.createConstructorWithVarArgsMathSet();
    }

    private MathSetImpl createConstructorWithMathSet() throws InterruptedException {
        System.out.println("I creat MathSet for you ;)");
        System.out.print("Paste MathSet random in your set");
        for (int i = 0; i < 4; i++) {
            Thread.sleep(500);
            System.out.print(" .");
        }
        System.out.println("Successfull");
        return RandomLongArray.createConstructorWithMathSet();
    }

    private MathSetImpl createConstructorWithVargArgsNumber() throws InterruptedException {
        System.out.println("I creat VargArgsNumber for you ;)");
        System.out.print("Paste VargArgsNumber random in your set");
        for (int i = 0; i < 4; i++) {
            Thread.sleep(500);
            System.out.print(" .");
        }
        System.out.println("Successfull");
        return RandomLongArray.createConstructorWithVargArgsNumber();
    }

    private MathSetImpl createConstructorWithArrayNumber() throws InterruptedException {
        System.out.println("I creat array Number for you ;)");
        System.out.print("Paste array Number random in your set");
        for (int i = 0; i < 4; i++) {
            Thread.sleep(500);
            System.out.print(" .");
        }
        System.out.println("Successfull");
        return RandomLongArray.createConstructorWithArrayNumber();
    }

    private MathSetImpl createConstructorWithCapacity(BufferedReader reader) throws Exception {
        try {
            System.out.println("Entry size array: ");
            String str = reader.readLine();
            return new MathSetImpl<Long>(Integer.parseInt(str));
        } catch (Exception e) {
            throw new Exception("createConstructorWithCapacity");
        }
    }

    private MathSetImpl createEmptyConstructor() {
        System.out.println("You create empty MathSet");
        MathSetImpl<Long> mathSet = new MathSetImpl<Long>();
        fillArrZero(mathSet.getArrayMath());
        return mathSet;
    }

    private void fillArrZero(Number[] arrayMath) {
        for (int i = 0; i < arrayMath.length; i++) {
            arrayMath[i] = 0L;
        }
    }
}
