package ua.com.alevel.hw_3_oop.dataBase.impl;

import ua.com.alevel.hw_3_oop.dataBase.UserDB;
import ua.com.alevel.hw_3_oop.entity.User;
import ua.com.alevel.hw_3_oop.myList.ArrList;

public class SomeUserDB implements UserDB {

    private ArrList<User> list;
    private static SomeUserDB instance;
    private static long idUser = 0;

    private SomeUserDB() {
        list = new ArrList<>();
    }

    public static SomeUserDB getInstance() {
        if (instance == null) {
            instance = new SomeUserDB();
        }
        return instance;
    }

    public void create(User user) {
        user.setId(generateId());
        list.add(user);
    }

    @Override
    public void delete(Long id) {
        if (list.remove(id)) {
            --idUser;
        }
    }

    public void update(User user) {
        User curr = findById(user.getId());
        curr.setName(user.getName());
        curr.setAge(user.getAge());
    }

    private Long generateId() {
        return ++idUser;
    }

    public User findById(Long id) {
        return (User) list.get(id);
    }

    public ArrList<User> findAll() {
        return list;
    }

}
