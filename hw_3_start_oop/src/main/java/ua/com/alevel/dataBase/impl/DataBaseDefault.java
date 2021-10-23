package ua.com.alevel.dataBase.impl;

import ua.com.alevel.dataBase.UserDB;
import ua.com.alevel.entity.User;
import ua.com.alevel.myList.ArrList;
import ua.com.alevel.utils.DataBaseUtils;

public class DataBaseDefault implements UserDB {

    private final ArrList<User> list;
    private static DataBaseDefault instance;

    private DataBaseDefault() {
        list = new ArrList<>();
    }

    public static DataBaseDefault getInstance() {
        if (instance == null) {
            instance = new DataBaseDefault();
        }
        return instance;

    }

    public void create(User user) {
        user.setId(DataBaseUtils.generateId(list));
        list.add(user);
    }

    public void drop(String id) {
        list.remove(id);
    }

    public void update(User user) {
        User curr = findById(user.getId());
        curr.setName(user.getName());
        curr.setAge(user.getAge());
    }

    public User findById(String id) {
        return (User) list.get(id);
    }

    public ArrList<User> findAll() {
        return list;
    }

    @Override
    public boolean findEmail(String email) {
        for (int i = 0; i < list.size(); i++) {
            User user = (User) list.get(i);
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
