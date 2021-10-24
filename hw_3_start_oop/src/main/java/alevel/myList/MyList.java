package alevel.myList;


import alevel.entity.BaseEntity;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyList {

    private final int CAPACITY = 16;
    private int size = 0;

    private int positionIter = 0;

    private BaseEntity[] list;

    public MyList() {
        list =  new BaseEntity[CAPACITY];
    }

    public int size() {
        return size;
    }

    protected void increaseSizeArr() {
        list = Arrays.copyOf(list, list.length * 2);

    }


    public boolean add(Object ob) {
        if (list.length == size) {
            increaseSizeArr();
        }
        list[size++] = (BaseEntity) ob;
        return true;
    }


    private boolean remove(int index) {
        list[index] = null;
        --size;
        reductionSizeArr(index);
        return true;
    }


    public boolean remove(Long id) {

            for (int i = 0; i < list.length; i++) {
                if (list[i].getId().equals(id)) {
                    return remove(i);
                }
        }
        return false;
    }

    public boolean remove(Object ob) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(ob)) {
                return remove(i);
            }
        }
        return false;
    }


    public Object get(int index) {
        if (index >= list.length) {
            throw new IndexOutOfBoundsException();
        }
        return list[index];
    }

    public Object get(Long id) {
        for (int i = 0; i < list.length; i++) {
                if (list[i].getId().equals(id)) {
                    return get(i);
                }

        }
        return null;
    }

    public Object get(Object ob) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(ob)) {
               return get(i);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        findAndDropNull(list);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
                stringBuilder.append(list[i]).append("\n");
        }
        return stringBuilder.toString();
    }


    private void reductionSizeArr(int indexDrop) {
        var first = Arrays.copyOfRange(list, 0, indexDrop);
        var second = Arrays.copyOfRange(list, indexDrop + 1, list.length);
        list = (BaseEntity[]) ArrayUtils.addAll(first, second);
    }

    private void findAndDropNull(BaseEntity[] objects) {
        for (int i = 0; i < list.length; i++) {
            if (list.length <= 1) {
                return;
            }
            if (list[i] == null) {
                reductionSizeArr(i);
                findAndDropNull(list);
            }
        }
    }

    public boolean isEmpty() {
        if (list.length < 1 || list == null) {
            return true;
        } else {
            return false;
        }
    }


    protected boolean hasNext() {
        return positionIter < list.length;
    }

    public Object next() {
        findAndDropNull(list);
        if(hasNext()) {
            return list[positionIter++];
        } else {
            throw new NoSuchElementException();
        }
    }

}
