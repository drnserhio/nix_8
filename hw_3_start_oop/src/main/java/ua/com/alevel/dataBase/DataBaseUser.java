package ua.com.alevel.dataBase;

import ua.com.alevel.List.MyList;
import ua.com.alevel.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataBaseUser {

    private MyList list;
    private static DataBaseUser instance;

    private DataBaseUser() {
        list = new MyList<>();
    }

    public static DataBaseUser getInstance() {
        if (instance == null) {
            instance = new DataBaseUser();
        }
        return instance;

    }

    public void create(User user) {
        user.setId(generateId());
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

    private String generateId() {
        String id = UUID.randomUUID().toString();
        if (list.contains(id)) {
            return generateId();
        }
        return id;
    }

    public User findById(String id) {
        return list.get(id);
    }

    public MyList<User> findAll() {
        return list;
    }
}
