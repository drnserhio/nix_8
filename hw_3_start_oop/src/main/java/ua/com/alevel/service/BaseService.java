package ua.com.alevel.service;

import ua.com.alevel.entity.BaseEntity;

import ua.com.alevel.myList.MyList;

public interface BaseService <T extends BaseEntity> {
    void create(T Entity);
    void drop(String id);
    void update(T entity);
    T findById(String id);
    MyList findAll();
}
