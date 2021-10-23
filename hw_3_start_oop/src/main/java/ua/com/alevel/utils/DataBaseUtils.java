package ua.com.alevel.utils;

import ua.com.alevel.myList.MyList;


import java.util.UUID;

public final class DataBaseUtils {

    private DataBaseUtils() {}

    public static String generateId(MyList list) {
        String id = UUID.randomUUID().toString();
        try {
            if (!list.get(id).equals(null)) {
                generateId(list);
            }
        } catch (NullPointerException e) {
            return id;
        }
        return "";
    }

}
