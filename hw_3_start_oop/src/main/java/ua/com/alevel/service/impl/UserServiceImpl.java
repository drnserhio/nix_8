package ua.com.alevel.service.impl;

import ua.com.alevel.config.DaoFactory;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.entity.User;
import ua.com.alevel.myList.MyList;
import ua.com.alevel.service.UserService;

public class UserServiceImpl implements UserService {


    private final UserDao userDao = DaoFactory.getInstance().getDataBaseObject(UserDao.class);

    @Override
    public void create(User entity) {
        if (!userDao.existsEmail(entity.getEmail())) {
            userDao.create(entity);
        } else {
            System.out.println("user exist by email");
        }

    }

    @Override
    public void drop(String id) {
        userDao.drop(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public MyList findAll() {
        return userDao.findAll();
    }
}
