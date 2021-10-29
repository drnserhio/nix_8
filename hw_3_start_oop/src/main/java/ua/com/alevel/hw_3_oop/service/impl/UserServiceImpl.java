package ua.com.alevel.hw_3_oop.service.impl;

import annotation.Autowired;
import annotation.Service;
import ua.com.alevel.hw_3_oop.dao.UserDao;
import ua.com.alevel.hw_3_oop.entity.User;
import ua.com.alevel.hw_3_oop.myList.ArrList;
import ua.com.alevel.hw_3_oop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }


    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public ArrList<User> findAll() {
        return userDao.findAll();
    }
}
