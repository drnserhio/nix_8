package ua.com.alevel.hw_3_oop.dataBase;


import ua.com.alevel.hw_3_oop.entity.BaseEntity;
import ua.com.alevel.hw_3_oop.myList.ArrList;

public interface BaseDB<ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(Long id);
    ENTITY findById(Long id);
    ArrList<ENTITY> findAll();
}
