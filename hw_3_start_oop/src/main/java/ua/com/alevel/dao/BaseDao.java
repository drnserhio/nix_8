package ua.com.alevel.dao;

import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.myList.MyList;


public interface BaseDao <T extends BaseEntity>{

    void create(T entity);
    void drop(String id);
    void update(T entity);
    T findById(String id);
    MyList findAll();

}
