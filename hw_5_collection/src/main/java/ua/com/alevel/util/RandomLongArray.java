package ua.com.alevel.util;

import ua.com.alevel.MathSetImpl;

import java.util.Random;

public class RandomLongArray {



    public static MathSetImpl createConstructorWithVarArgsMathSet() {
        MathSetImpl[] mathSet = createArrSetWithArrays();
        return new MathSetImpl<Long>(mathSet);
    }

    public static MathSetImpl[] createArrSetWithArrays() {
        MathSetImpl[] sets = new MathSetImpl[randomSize()];
        for (int i = 0; i < sets.length; i++) {
            sets[i] = new MathSetImpl<Long>(randomArray());
        }
        return sets;
    }

    public static  MathSetImpl createConstructorWithMathSet() {
        MathSetImpl mathSet = createSetWithArray();
        return new MathSetImpl<Long>(mathSet);
    }

    public static  MathSetImpl createSetWithArray() {
        Number[] array = randomArray();
        return new MathSetImpl<Long>(array);
    }

    public static  MathSetImpl createConstructorWithVargArgsNumber() {
        Number[][] array = randomDoubleArray();
        return new MathSetImpl<Long>(array);
    }

    public static  Number[][] randomDoubleArray() {
        Number[][] array = new Number[randomSize()][randomSize()];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = randomValue();
            }
        }
        return array;
    }

    public static  MathSetImpl createConstructorWithArrayNumber() {
        Number[] array = randomArray();
        return new MathSetImpl<Long>(array);
    }

    public static  Number[] randomArray() {
        Number[] array = new Number[randomSize()];
        for (int i = 0; i < array.length; i++) {
            array[i] = randomValue();
        }
        return array;
    }

    public static  Number randomValue() {
        return new Random().nextLong(30L);
    }

    public static  int randomSize() {
        return new Random().nextInt(30);
    }
}
