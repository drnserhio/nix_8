package ua.com.alevel.service;

import ua.com.alevel.List.MyList;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.entity.User;

public class UserService {

    private final UserDao userDao = new UserDao();

    public void create(User user) {
        userDao.create(user);
    }

    public void drop(String id) {
        userDao.drop(id);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public User finById(String id) {
        return userDao.findById(id);
    }

    public MyList<User> findAll() {
        return userDao.findAll();
    }
}
