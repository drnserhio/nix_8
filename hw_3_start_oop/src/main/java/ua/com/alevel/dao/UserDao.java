package ua.com.alevel.dao;

import ua.com.alevel.dataBase.DataBase;
import ua.com.alevel.entity.User;
import ua.com.alevel.myList.ArrList;
import ua.com.alevel.myList.MyList;

public class UserDao {

    public void create(User user) {
        DataBase.getInstance().create(user);
    }

    public void drop(Long id) {
        DataBase.getInstance().drop(id);
    }

    public void update(User user) {
        DataBase.getInstance().update(user);
    }

    public User findById(Long id) {
        return DataBase.getInstance().findById(id);
    }

    public ArrList<User> findAll() {
        return DataBase.getInstance().findAll();
    }
}
