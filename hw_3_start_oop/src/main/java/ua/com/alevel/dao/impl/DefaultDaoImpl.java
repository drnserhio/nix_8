package ua.com.alevel.dao.impl;

import ua.com.alevel.config.DaoFactory;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.dataBase.UserDB;
import ua.com.alevel.entity.User;
import ua.com.alevel.myList.MyList;


public class DefaultDaoImpl implements UserDao {

    private final UserDB userDB = DaoFactory.getInstance().getDataBaseObject(UserDB.class);

    @Override
    public void create(User entity) {
        userDB.create(entity);
    }

    @Override
    public void drop(String id) {
        userDB.drop(id);
    }

    @Override
    public void update(User entity) {
        userDB.update(entity);
    }

    @Override
    public User findById(String id) {
        return userDB.findById(id);
    }

    @Override
    public MyList findAll() {
        return userDB.findAll();
    }


    @Override
    public boolean existsEmail(String email) {
        return userDB.findEmail(email);
    }
}
