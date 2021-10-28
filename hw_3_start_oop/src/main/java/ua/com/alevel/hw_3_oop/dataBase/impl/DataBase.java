package ua.com.alevel.hw_3_oop.dataBase.impl;


import ua.com.alevel.hw_3_oop.dataBase.UserDB;
import ua.com.alevel.hw_3_oop.entity.User;
import ua.com.alevel.hw_3_oop.myList.ArrList;

public class DataBase implements UserDB {

    private final ArrList<User> list;
    private static DataBase instance;
    private static long idUser = 0;

    private DataBase() {
        list = new ArrList<>();
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
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

    @Override
    public void update(User user) {
        User curr = findById(user.getId());
        curr.setName(user.getName());
        curr.setAge(user.getAge());
    }

    private Long generateId() {
        return ++idUser;
    }

    @Override
    public User findById(Long id) {
        return (User) list.get(id);
    }

    @Override
    public ArrList<User> findAll() {
        return list;
    }

}
