package alevel.dao;


import alevel.dataBase.DataBase;
import alevel.entity.User;
import alevel.myList.ArrList;

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
