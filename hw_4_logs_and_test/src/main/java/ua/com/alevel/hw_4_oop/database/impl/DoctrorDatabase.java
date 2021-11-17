package ua.com.alevel.hw_4_oop.database.impl;

import ua.com.alevel.hw_4_oop.database.DoctorDB;
import ua.com.alevel.hw_4_oop.entity.Doctor;
import ua.com.alevel.hw_4_oop.mylist.DoctorList;

import java.util.Optional;

public class DoctrorDatabase implements DoctorDB {

    private final DoctorList list;
    private static DoctrorDatabase instance;
    private static long idUser = 0;

    public DoctrorDatabase() {
        list = new DoctorList();
    }

    public static DoctrorDatabase getInstance() {
        if (instance == null) {
            instance = new DoctrorDatabase();
        }
        return instance;

    }

    public void create(Doctor doctor) {
        doctor.setId(generateId());
        list.add(doctor);
    }

    @Override
    public void delete(Long id) {
        if (list.remove(id)) {
            --idUser;
        }
    }

    @Override
    public void update(Doctor doctor) {
        Doctor curr = findById(doctor.getId());
        curr.setName(doctor.getName());
        curr.setAge(doctor.getAge());
    }

    private Long generateId() {
        return ++idUser;
    }

    @Override
    public Doctor findById(Long id) {
        return (Doctor) list.get(id);
    }

    @Override
    public Optional<DoctorList> findAll() {
        return Optional.of(list);
    }

    public Optional<Doctor> findByName(String name) {
        return list.findByName(name);
    }
}
