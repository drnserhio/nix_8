package ua.com.alevel.hw_3_oop.dao.impl;


import annotation.Service;
import ua.com.alevel.hw_3_oop.config.ObjectFactory;
import ua.com.alevel.hw_3_oop.dao.UserDao;
import ua.com.alevel.hw_3_oop.dataBase.UserDB;
import ua.com.alevel.hw_3_oop.dataBase.impl.DataBase;
import ua.com.alevel.hw_3_oop.entity.User;
import ua.com.alevel.hw_3_oop.myList.ArrList;

@Service
public class UserDaoImpl implements UserDao {
    private final UserDB db = new DataBase();

    public void create(User user) {
        DataBase.getInstance().create(user);
    }

    public void delete(Long id) {
        DataBase.getInstance().delete(id);
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
