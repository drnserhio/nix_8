package ua.com.alevel.hw_4_oop.dao.impl;

import ua.com.alevel.hw_4_oop.dao.DoctorDao;
import ua.com.alevel.hw_4_oop.database.DoctorDB;
import ua.com.alevel.hw_4_oop.database.impl.DoctrorDatabase;
import ua.com.alevel.hw_4_oop.entity.Doctor;
import ua.com.alevel.hw_4_oop.mylist.DoctorList;

import java.util.Optional;

public class DoctorDaoImpl implements DoctorDao {

    public void create(Doctor doctor) {
        DoctrorDatabase.getInstance().create(doctor);
    }

    public void delete(Long id) {
        DoctrorDatabase.getInstance().delete(id);
    }

    public void update(Doctor doctor) {
        DoctrorDatabase.getInstance().update(doctor);
    }

    public Doctor findById(Long id) {
        return DoctrorDatabase.getInstance().findById(id);
    }

    public Optional<DoctorList> findAll() {
        return DoctrorDatabase.getInstance().findAll();
    }

    public Optional<Doctor> findByName(String name) {
        return DoctrorDatabase.getInstance().findByName(name);
    }
}
