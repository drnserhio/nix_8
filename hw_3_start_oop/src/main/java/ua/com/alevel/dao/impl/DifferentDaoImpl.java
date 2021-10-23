package ua.com.alevel.dao.impl;

import ua.com.alevel.config.DaoFactory;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.dataBase.UserDB;
import ua.com.alevel.entity.User;
import ua.com.alevel.myList.MyList;

@Deprecated
public class DifferentDaoImpl implements UserDao {

    private final UserDB db = DaoFactory.getInstance().getDataBaseObject(UserDB.class);

    @Override
    public void create(User entity) {
        db.create(entity);
    }

    @Override
    public void drop(String id) {
        db.drop(id);
    }

    @Override
    public void update(User entity) {
        db.update(entity);
    }

    @Override
    public User findById(String id) {
        return db.findById(id);
    }

    @Override
    public MyList findAll() {
        return db.findAll();
    }

    @Override
    public boolean existsEmail(String email) {
        return db.findEmail(email);
    }

}
