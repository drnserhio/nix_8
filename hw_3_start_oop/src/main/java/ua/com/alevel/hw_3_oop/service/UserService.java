package ua.com.alevel.hw_3_oop.service;

import annotation.Service;
import ua.com.alevel.hw_3_oop.config.ObjectFactory;
import ua.com.alevel.hw_3_oop.dao.UserDao;
import ua.com.alevel.hw_3_oop.dao.impl.UserDaoImpl;
import ua.com.alevel.hw_3_oop.entity.User;
import ua.com.alevel.hw_3_oop.myList.ArrList;

@Service
public class UserService {


    private final UserDao userDao = ObjectFactory.getInstance().getCurrentObject(UserDao.class);

    public void create(User user) {
        userDao.create(user);
    }

    public void drop(Long id) {
        userDao.delete(id);
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
