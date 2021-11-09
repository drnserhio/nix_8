package ua.com.alevel.hw_4_oop.dataBase;


import ua.com.alevel.hw_4_oop.entity.Patient;
import ua.com.alevel.hw_4_oop.myList.ArrList;

public interface BaseDB<ENTITY extends Patient> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(Long id);
    ENTITY findById(Long id);
    ArrList<ENTITY> findAll();
}
