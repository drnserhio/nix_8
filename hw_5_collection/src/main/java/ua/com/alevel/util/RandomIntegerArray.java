package ua.com.alevel.util;

import ua.com.alevel.resource.MathSetImpl;
import java.util.Random;

public class RandomIntegerArray {

    public static MathSetImpl createConstructorWithVarArgsMathSet() {
        MathSetImpl[] mathSet = createArrSetWithArrays();
        return new MathSetImpl<Integer>(mathSet);
    }

    public static MathSetImpl[] createArrSetWithArrays() {
        MathSetImpl[] sets = new MathSetImpl[randomSize()];
        for (int i = 0; i < sets.length; i++) {
            sets[i] = new MathSetImpl<Integer>(randomArray());
        }
        return sets;
    }

    public static  MathSetImpl createConstructorWithMathSet() {
        MathSetImpl mathSet = createSetWithArray();
        return new MathSetImpl<Integer>(mathSet);
    }

    public static  MathSetImpl createSetWithArray() {
        Number[] array = randomArray();
        return new MathSetImpl<Integer>(array);
    }

    public static  MathSetImpl createConstructorWithVargArgsNumber() {
        Number[][] array = randomDoubleArray();
        return new MathSetImpl<Integer>(array);
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
        return new MathSetImpl(array);
    }

    public static  Number[] randomArray() {
        Number[] array = new Number[randomSize()];
        for (int i = 0; i < array.length; i++) {
            array[i] = randomValue();
        }
        return array;
    }

    public static  Number randomValue() {
        return randomSize();
    }

    public static  int randomSize() {
        return new Random().nextInt(30);
    }
}
