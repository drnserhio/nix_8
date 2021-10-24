package ua.com.alevel.service;

import ua.com.alevel.dao.UserDao;
import ua.com.alevel.entity.User;
import ua.com.alevel.myList.ArrList;
import ua.com.alevel.myList.MyList;

public class UserService {


    private final UserDao userDao = new UserDao();

    public void create(User user) {
        userDao.create(user);
    }

    public void drop(Long id) {
        userDao.drop(id);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public User finById(Long id) {
        return userDao.findById(id);
    }

    public ArrList<User> findAll() {
        return userDao.findAll();
    }
}
