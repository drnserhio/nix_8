package ua.com.alevel.hw_3_oop.dataBase;


import ua.com.alevel.hw_3_oop.entity.User;
import ua.com.alevel.hw_3_oop.myList.ArrList;

public class DataBase {

    private ArrList<User> list;
    private static DataBase instance;
    private static long idUser = 0;

    private DataBase() {
        list = new ArrList<>();
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;

    }

    public void create(User user) {
        user.setId(generateId());
        list.add(user);
    }

    public void drop(Long id) {
        if(list.remove(id)){
            --idUser;
        }


    }

    public void update(User user) {
        User curr = findById(user.getId());
        curr.setName(user.getName());
        curr.setAge(user.getAge());
    }

    private Long generateId() {
        return ++idUser;
    }

    public User findById(Long id) {
        return (User) list.get(id);
    }

    public ArrList<User> findAll() {
        return list;
    }
}
