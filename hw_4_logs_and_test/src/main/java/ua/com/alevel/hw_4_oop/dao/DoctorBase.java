package ua.com.alevel.hw_4_oop.dao;

import ua.com.alevel.hw_4_oop.entity.Doctor;
import ua.com.alevel.hw_4_oop.myList.DoctorList;

import java.util.Optional;

public interface DoctorBase <ENTITY extends Doctor> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(Long id);
    ENTITY findById(Long id);
    Optional<DoctorList> findAll();
}
