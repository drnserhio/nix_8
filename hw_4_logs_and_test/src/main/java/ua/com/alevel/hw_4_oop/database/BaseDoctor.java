package ua.com.alevel.hw_4_oop.database;

import ua.com.alevel.hw_4_oop.entity.Doctor;
import ua.com.alevel.hw_4_oop.mylist.DoctorList;

import java.util.Optional;

public interface BaseDoctor <ENTITY extends Doctor>{
    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(Long id);
    ENTITY findById(Long id);
    Optional<DoctorList> findAll();
}
