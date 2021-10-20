package ua.com.alevel.dao;

import ua.com.alevel.List.MyList;
import ua.com.alevel.dataBase.DataBaseUser;
import ua.com.alevel.entity.User;

public class UserDao {

    public void create(User user) {
        DataBaseUser.getInstance().create(user);
    }

    public void drop(String id) {
        DataBaseUser.getInstance().drop(id);
    }

    public void update(User user) {
        DataBaseUser.getInstance().update(user);
    }

    public User findById(String id) {
        return DataBaseUser.getInstance().findById(id);
    }

    public MyList<User> findAll() {
       return DataBaseUser.getInstance().findAll();
    }
}
