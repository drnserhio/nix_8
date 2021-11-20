package ua.com.alevel.resource;

import ua.com.alevel.exception.EmptyArrayException;
import ua.com.alevel.exception.NumberNullException;
import ua.com.alevel.util.OffsetArrayHelper;
import ua.com.alevel.util.SortArray;

import java.util.Arrays;

import static ua.com.alevel.StringerUtil.result;
import static ua.com.alevel.constant.ExceptionConstant.*;

public class MathSetImpl<N extends Number> implements MathSetInterface {

    private Number[] arrayMath;
    private Number[] workArray;

    private int size = 0;
    private static final int CAPACITY = 10;
    private Number fisrtNumber;

    public MathSetImpl() {
        arrayMath = new Number[CAPACITY];
        this.fisrtNumber = arrayMath[0];
    }

    public MathSetImpl(int capacity) {
        arrayMath = new Number[capacity];
        this.fisrtNumber = arrayMath[0];
    }

    public MathSetImpl(Number[] numbers) {
        if (numbers == null ||
                numbers.length < 1) {

            this.arrayMath = new Number[CAPACITY];
        } else {
            this.arrayMath = numbers;
            this.fisrtNumber = arrayMath[0];
            this.size = arrayMath.length;
        }
    }

    public MathSetImpl(Number[]... numbers) {
        if (numbers.length < 1 ||
                numbers == null) {
            this.arrayMath = new Number[CAPACITY];
        } else {
            this.arrayMath = varArgsToArray(numbers);
            this.fisrtNumber = arrayMath[0];
            this.size = arrayMath.length;
        }
    }

    public MathSetImpl(MathSetImpl number) {
        if (number == null ||
                number.size() < 1) {
            this.arrayMath = new Number[CAPACITY];
        } else {
            this.arrayMath = objectToArray(number);
            this.fisrtNumber = arrayMath[0];
            this.size = arrayMath.length;
        }
    }

    public MathSetImpl(MathSetImpl... numbers) {
        if (numbers == null) {
            this.arrayMath = new Number[CAPACITY];
        } else {
            this.arrayMath = objectsToArray(numbers);
            this.fisrtNumber = arrayMath[0];
            this.size = arrayMath.length;
        }
    }

    @Override
    public void add(Number n) {
        if (size > 0) {
            if (isValueHasInSet(n)) {
                return;
            }
        }
        if (arrayMath.length == size) {
            increaseSizeArr();
        }
        arrayMath[size++] = isNumberType(fisrtNumber, n);
    }

    private void increaseSizeArr() {
        this.arrayMath = Arrays.copyOf(arrayMath, (arrayMath.length) * 2);
    }

    @Override
    public void add(Number... n) {
        if (size > 0) {
            for (int i = 0; i < n.length; i++) {

                if (isValueHasInSet(n[i])) {
                    return;
                }
                n[i] = isNumberType(fisrtNumber, n[i]);
            }
        }
        this.arrayMath = killDublicate(killNullPointer(SortArray.joinArray(arrayMath, n)));
        this.size = arrayMath.length;
    }

    public boolean isValueHasInSet(Number n) {
        for (int i = 0; i < arrayMath.length; i++) {
            if (arrayMath[i].equals(n)) {
                return true;
            }
        }
        return false;
    }

    private Number isNumberType(Number currenNumber, Number number) {

        if (currenNumber instanceof Integer) {
            return number.intValue();

        }
        if (currenNumber instanceof Long) {
            return number.longValue();
        }
        if (currenNumber instanceof Double) {
            return number.doubleValue();

        }
        if (currenNumber instanceof Float) {
            return number.floatValue();

        }
        return number;
    }

    @Override
    public void join(MathSetImpl ms) {
        try {
            Number[] arr = ms.killNullPointer(killNullPointer(ms.getArrayMath()));
            if (arrayMath.length > 1) {
                this.arrayMath = SortArray.joinArray(
                        killDublicate(
                                killNullPointer(arrayMath)), arr);
                SortArray.sort(arrayMath);
                this.size = arrayMath.length;
            } else {
                throw new EmptyArrayException(EMPTY_ARRAY + Arrays.toString(arrayMath));
            }
        } catch (EmptyArrayException e) {
            throw new EmptyArrayException(EMPTY_ARRAY + "in MathSetImpl object");
        }
    }

    @Override
    public void join(MathSetImpl... ms) {
        try {
            if (arrayMath.length > 1) {
                for (MathSetImpl ob : ms) {
                    Number[] joiningArray = ob.killNullPointer(killNullPointer(ob.getArrayMath()));
                    this.arrayMath = SortArray.joinArray(arrayMath, joiningArray);
                }
                this.arrayMath = killDublicate(killNullPointer(arrayMath));
                SortArray.sort(arrayMath);
            } else {
                throw new EmptyArrayException(EMPTY_ARRAY + Arrays.toString(arrayMath));
            }
        } catch (EmptyArrayException e) {
            throw new EmptyArrayException(EMPTY_ARRAY + "in MathSetImpl object");
        }
    }

    @Override
    public void intersection(MathSetImpl ms) {
        if (arrayMath.length > 1) {
            this.arrayMath = findIntersection(ms.getArrayMath());
            this.size = arrayMath.length;
        } else {
            throw new EmptyArrayException(EMPTY_ARRAY + Arrays.toString(arrayMath));
        }
    }

    @Override
    public void intersection(MathSetImpl... ms) {
        if (arrayMath.length > 1) {
            MathSetImpl mathSet = new MathSetImpl(ms);
            this.arrayMath = findIntersection(mathSet.getArrayMath());
            this.size = this.arrayMath.length;

        } else {
            throw new EmptyArrayException(EMPTY_ARRAY + Arrays.toString(arrayMath));
        }
    }

    private Number[] findIntersection(Number[] numbers) {
        Number[] number = new Number[100];
        for (int i = 0; i < arrayMath.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (arrayMath[i].equals(numbers[j])) {
                    number[i] = arrayMath[i];
                }
            }
        }
        return killDublicate(killNullPointer(number));
    }

    @Override
    public void sortDesc() {
        if (arrayMath.length > 1) {
            this.arrayMath = killDublicate(killNullPointer(arrayMath));
            SortArray.sortDesk(arrayMath);
        } else {
            throw new EmptyArrayException(EMPTY_ARRAY + Arrays.toString(arrayMath));
        }
    }

    @Override
    public void sortDesc(int firstIndex, int lastIndex) {
        if (arrayMath.length > 1) {
            this.arrayMath = killDublicate(killNullPointer(arrayMath));
            if (isNotOutBoundArrayIndex(firstIndex, lastIndex)) {
                if (firstIndex > 0 && lastIndex < arrayMath.length) {
                    this.arrayMath = OffsetArrayHelper.sortedMiddleDesk(arrayMath, firstIndex, lastIndex);
                }
                if (firstIndex <= 0 && lastIndex < arrayMath.length) {
                    this.arrayMath = OffsetArrayHelper.sortedStartDesk(arrayMath, firstIndex, lastIndex);
                }
                if (firstIndex > 0 && lastIndex == arrayMath.length) {
                    this.arrayMath = OffsetArrayHelper.sortedEndDesk(arrayMath, firstIndex, lastIndex);
                }
            } else {
                throw new IndexOutOfBoundsException(OUT_INDEX_ARRAY);
            }
        } else {
            throw new EmptyArrayException(EMPTY_ARRAY + Arrays.toString(arrayMath));
        }
    }

    @Override
    public void sortDesc(Number value) {
        if (arrayMath.length > 1) {
            int index = findIndexNumberByValue(value);
            int lenght = arrayMath.length;
            sortDesc(index, lenght);
        } else {
            throw new EmptyArrayException(EMPTY_ARRAY + Arrays.toString(arrayMath));
        }
    }

    private int findIndexNumberByValue(Number value) {
        if (arrayMath.length > 1) {
            for (int i = 0; i < arrayMath.length; i++) {
                if (arrayMath[i].equals(value)) {
                    return i;
                }
            }
        } else {
            throw new EmptyArrayException(EMPTY_ARRAY + Arrays.toString(arrayMath));
        }
        return -1;
    }

    @Override
    public void sortAsc() {
        if (arrayMath.length > 1) {
            this.arrayMath = killDublicate(killNullPointer(arrayMath));
            SortArray.sort(arrayMath);

        } else {
            throw new EmptyArrayException(EMPTY_ARRAY + Arrays.toString(arrayMath));
        }
    }

    @Override
    public void sortAsc(int firstIndex, int lastIndex) {
        if (arrayMath.length > 1) {
            this.arrayMath = killDublicate(killNullPointer(arrayMath));
            if (isNotOutBoundArrayIndex(firstIndex, lastIndex)) {
                if (firstIndex > 0 && lastIndex < arrayMath.length) {
                    this.arrayMath = OffsetArrayHelper.sortedMiddleAsc(arrayMath, firstIndex, lastIndex);
                }
                if (firstIndex <= 0 && lastIndex < arrayMath.length) {
                    this.arrayMath = OffsetArrayHelper.sortedStartAsc(arrayMath, firstIndex, lastIndex);
                }
                if (firstIndex > 0 && lastIndex == arrayMath.length) {
                    this.arrayMath = OffsetArrayHelper.sortedEndAsc(arrayMath, firstIndex, lastIndex);
                }
            } else {
                throw new IndexOutOfBoundsException(OUT_INDEX_ARRAY);
            }
        } else {
            throw new EmptyArrayException(EMPTY_ARRAY + Arrays.toString(arrayMath));
        }
    }

    @Override
    public void sortAsc(Number value) {
        if (arrayMath.length > 1) {
            int index = findIndexNumberByValue(value);
            int lenght = arrayMath.length;
            sortAsc(index, lenght);
        } else {
            throw new EmptyArrayException(EMPTY_ARRAY + Arrays.toString(arrayMath));
        }
    }


    @Override
    public Number get(int index) {
        if (index >= arrayMath.length) {
            throw new IndexOutOfBoundsException(OUT_INDEX_ARRAY + (arrayMath.length - index));
        }
        return this.arrayMath[index];
    }

    @Override
    public Number getMax() {
        if (arrayMath.length > 1) {
            Number maxNumber = arrayMath[0];
            for (int i = 1; i < arrayMath.length; i++) {
                if (maxNumber.intValue() < arrayMath[i].intValue()) {
                    maxNumber = arrayMath[i];
                }
            }
            return maxNumber;
        } else {
            throw new EmptyArrayException(EMPTY_ARRAY + Arrays.toString(arrayMath));
        }
    }

    @Override
    public Number getMin() {
        if (arrayMath.length > 1) {
            Number minNumber = arrayMath[0];
            for (int i = 1; i < arrayMath.length; i++) {
                if (minNumber.intValue() > arrayMath[i].intValue()) {
                    minNumber = arrayMath[i];
                }
            }
            return minNumber;
        } else {
            throw new EmptyArrayException(EMPTY_ARRAY + Arrays.toString(arrayMath));
        }
    }

    @Override
    public Number getAverage() {
        double average = 0;
        if (arrayMath.length > 1) {
            this.arrayMath = killDublicate(killNullPointer(arrayMath));
            for (int i = 0; i < arrayMath.length; i++) {
                average += arrayMath[i].doubleValue();
            }
            return average / arrayMath.length;
        } else {
            throw new EmptyArrayException(EMPTY_ARRAY + Arrays.toString(arrayMath));
        }
    }

    @Override
    public Number getMedian() {
        Number median = 0;
        if (arrayMath.length > 1) {
            this.arrayMath = killDublicate(killNullPointer(arrayMath));
            if (arrayMath[0] instanceof Double || arrayMath[0] instanceof Float) {
                if (arrayMath.length % 2 == 0) {
                    median = (arrayMath[arrayMath.length / 2 - 1].doubleValue() + (arrayMath[arrayMath.length / 2].doubleValue())) / 2;
                } else {
                    median = arrayMath[arrayMath.length / 2].doubleValue();
                }
            } else {
                if (arrayMath.length % 2 == 0) {
                    median = (arrayMath[arrayMath.length / 2 - 1].intValue() + (arrayMath[arrayMath.length / 2].intValue())) / 2;
                } else {
                    median = arrayMath[arrayMath.length / 2].intValue();
                }
            }
            return median;
        } else {
            throw new EmptyArrayException(EMPTY_ARRAY + Arrays.toString(arrayMath));
        }
    }

    @Override
    public Number[] toArray() {
        if (arrayMath.length > 1) {
            this.arrayMath = killDublicate(killNullPointer(arrayMath));
            this.size = arrayMath.length;
            return arrayMath;
        } else {
            throw new EmptyArrayException(EMPTY_ARRAY + Arrays.toString(arrayMath));
        }
    }

    @Override
    public Number[] toArray(int firstIndex, int lastIndex) {
        return killDublicate(killNullPointer(arrayFromToIndex(firstIndex, lastIndex)));
    }

    @Override
    public MathSetImpl cut(int firstIndex, int lastIndex) {
        if (arrayMath.length > 1) {
            this.arrayMath = SortArray.cutArray(
                    killDublicate(killNullPointer(arrayMath))
                    , firstIndex, lastIndex + 1
            );
            return new MathSetImpl(this.arrayMath);
        } else {
            throw new EmptyArrayException(EMPTY_ARRAY + Arrays.toString(arrayMath));
        }
    }

    private Number[] arrayFromToIndex(int firstIndex, int lastIndex) {
        if (arrayMath.length > 1) {
            if (isNotOutBoundArrayIndex(firstIndex, lastIndex)) {
                Number[] newArray = new Number[firstIndex + lastIndex];
                for (int i = firstIndex; i <= lastIndex; i++) {
                    newArray[i] = arrayMath[i];
                }
                this.size = newArray.length;
                return newArray;
            } else {
                throw new IndexOutOfBoundsException(OUT_INDEX_ARRAY);
            }
        } else {
            throw new EmptyArrayException(EMPTY_ARRAY + Arrays.toString(arrayMath));
        }
    }

    private boolean isNotOutBoundArrayIndex(int firstIndex, int lastIndex) {
        if (firstIndex > lastIndex ||
                firstIndex > arrayMath.length ||
                firstIndex <= -1 ||
                lastIndex > arrayMath.length) {
            return false;
        }
        return true;
    }

    @Override
    public void clear() {
        this.arrayMath = new Number[CAPACITY];
        this.size = arrayMath.length;
    }

    @Override
    public void clear(Number[] numbers) {
        if (arrayMath.length > 1) {
            this.arrayMath = clearWithArray(numbers);
            this.size = arrayMath.length;
        } else {
            throw new EmptyArrayException(EMPTY_ARRAY + Arrays.toString(arrayMath));
        }
    }

    private Number[] clearWithArray(Number[] numbers) {
        for (int i = 0; i < arrayMath.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (arrayMath[i] != null) {
                    if (arrayMath[i].equals(numbers[j])) {
                        arrayMath[i] = null;
                    }
                }
            }
        }
        return killDublicate(killNullPointer(arrayMath));
    }

    private Number[] varArgsToArray(Number[][] numbers) {
        this.workArray = addAllArrays(numbers);
        this.size = workArray.length;
        return killDublicate(workArray);
    }

    private Number[] objectToArray(MathSetImpl number) {
        try {
            Number[] childArray = number.getArrayMath();
            int lenght = childArray.length;
            this.workArray = new Number[lenght];
            for (int i = 0; i < childArray.length; i++) {
                workArray[i] = childArray[i];
            }
            return killDublicate(workArray);
        } catch (Exception e) {
            throw new NumberNullException(HAS_NULL_NUMBER);
        }
    }

    private Number[] objectsToArray(MathSetImpl[] numbers) {
        this.workArray = addAllArrays(numbers);
        return killDublicate(workArray);
    }

    private Number[] addAllArrays(MathSetImpl[] numbers) {
        try {
            Number[] arr = killNullPointer(numbers[0].getArrayMath());
            for (int i = 1; i < numbers.length; i++) {
                Number[] withoutNullElementArr = killNullPointer(numbers[i].getArrayMath());
                var first = SortArray.cutArray(arr, 0, arr.length);
                var second = SortArray.cutArray(withoutNullElementArr, 0, withoutNullElementArr.length);
                arr = SortArray.joinArray(first, second);
            }
            return arr;
        } catch (Exception e) {
            throw new NumberNullException(HAS_NULL_NUMBER);
        }
    }

    private Number[] addAllArrays(Number[][] numbers) {
        try {
            Number[] arr = killNullPointer(numbers[0]);
            for (int i = 1; i < numbers.length; i++) {
                Number[] withoutNullElementArr = killNullPointer(numbers[i]);
                if (numbers[i] != null || numbers[i].length > 0) {
                    var first = SortArray.cutArray(arr, 0, arr.length);
                    var second = SortArray.cutArray(withoutNullElementArr, 0, withoutNullElementArr.length);
                    arr = SortArray.joinArray(first, second);
                }
            }
            return arr;
        } catch (Exception e) {
            throw new NumberNullException(HAS_NULL_NUMBER);
        }
    }

    private Number[] killNullPointer(Number[] arr) {
        Number[] newArray = new Number[countNotNullElement(arr)];
        int countNewArray = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                newArray[countNewArray++] = arr[i];
            }
        }
        this.size = newArray.length;
        return newArray;
    }

    private int countNotNullElement(Number[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                count++;
            }
        }
        return count;
    }

    private Number[] killDublicate(Number[] arr) {
        if (arr == null || arr.length == 0) return arr;
        int n = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) n++;
        }
        Number[] res = new Number[n];
        res[0] = arr[0];
        n = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) res[n++] = arr[i];
        }
        this.size = res.length;
        return res;

    }

    public Number[] getArrayMath() {
        return arrayMath;
    }

    public int size() {
        return size;
    }

    public void toPrint() {
        this.arrayMath = killDublicate(killNullPointer(arrayMath));
        result("\n" + Arrays.toString(arrayMath));
    }

    @Override
    public String toString() {
        this.arrayMath = killDublicate(killNullPointer(arrayMath));
        return Arrays.toString(arrayMath);
    }

}


