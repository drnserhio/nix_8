package ua.com.alevel.myList;

import org.apache.commons.lang.ArrayUtils;
import ua.com.alevel.entity.User;

import java.util.Arrays;

public class MyList<T extends User> {

    private final int CAPACITY = 16;
    private int size = 0;

    private User[] list = new User[CAPACITY];


    public int size() {
        return size;
    }

    public boolean add(User user) {
        if(increaseSizeArr()) {
            list[size++] = user;
        } else {
            list[size++] =  user;
        }
        return true;
    }

    private boolean increaseSizeArr() {
        if (list.length - 1 == size) {
            list = Arrays.copyOf(list, list.length * 2);
            return true;
        } else {
            return false;
        }
    }

    public boolean remove(Object ob) {
        boolean isHas = false;
        int indexDrop = -1;
        int length = list.length;

        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(ob)) {
                indexDrop = i;
                isHas = true;
                --size;
                break;
            }
        }
        if (!isHas) {
            reductionSizeArr(indexDrop, length);
        }
        return isHas;
    }

    public boolean remove(String id) {
        boolean isHas = false;
        int indexDrop = -1;
        int length = list.length;

        for (int i = 0; i < list.length; i++) {
            if (list[i].getId().equals(id)) {
                indexDrop = i;
                isHas = true;
                --size;
                break;
            }
        }
        if (isHas) {
            reductionSizeArr(indexDrop, length);
        }
        return isHas;
    }

    private void reductionSizeArr(int indexDrop, int length) {
        var first = Arrays.copyOfRange(list, 0, indexDrop );
        var second = Arrays.copyOfRange(list, indexDrop + 1, length);
        list = (User[]) ArrayUtils.addAll(first, second);
    }

    public T get(String id) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].getId().equals(id)) {
                return (T) list[i];
            }
        }
        return null;
    }

    private void findAndDropNull(User[] users) {
        for (int i = 0; i < list.length; i++) {
            if (list.length <= 1) {
                return;
            }
           if (list[i] == null) {
               reductionSizeArr(i,list.length);
               findAndDropNull(list);
           }

        }
    }

    public boolean contains(String id) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                continue;
            }
            if (list[i].getId().equals(id)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        findAndDropNull(list);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.length; i++) {
                stringBuilder.append(list[i]);

        }
        return stringBuilder.toString();
    }

    public boolean isEmpty() {
        if (list.length < 1 || list == null) {
            return true;
        } else {
            return false;
        }
    }
}
