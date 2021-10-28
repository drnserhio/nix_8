package ua.com.alevel.hw_3_oop.dao;


import ua.com.alevel.hw_3_oop.entity.BaseEntity;
import ua.com.alevel.hw_3_oop.myList.ArrList;

public interface BaseDao<ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(Long id);
    ENTITY findById(Long id);
    ArrList<ENTITY> findAll();
}
