package ua.com.alevel.hw_4_oop.service.impl;

import ua.com.alevel.hw_4_oop.dao.impl.DoctorDaoImpl;
import ua.com.alevel.hw_4_oop.entity.Doctor;
import ua.com.alevel.hw_4_oop.mylist.DoctorList;
import ua.com.alevel.hw_4_oop.service.DocService;

import java.util.Optional;

public class DoctorService implements DocService {

    private DoctorDaoImpl userDao = new DoctorDaoImpl();

    @Override
    public void create(Doctor doctor) {
        userDao.create(doctor);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public void update(Doctor doctor) {
        userDao.update(doctor);
    }


    @Override
    public Doctor findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public Optional<DoctorList> findAll() {
        return userDao.findAll();
    }

    @Override
    public Optional<Doctor> findByName(String name) {
        return userDao.findByName(name);
    }
}
