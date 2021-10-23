package ua.com.alevel.dataBase;

import ua.com.alevel.entity.User;

public interface UserDB extends BaseBD<User>{
    boolean findEmail(String email);
}
