package ua.com.alevel.hw_4_oop.service.impl;

import annotation.Autowired;
import annotation.Service;
import ua.com.alevel.hw_4_oop.dao.UserDao;
import ua.com.alevel.hw_4_oop.entity.Patient;
import ua.com.alevel.hw_4_oop.mylist.ArrList;
import ua.com.alevel.hw_4_oop.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void create(Patient patient) {
        userDao.create(patient);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public void update(Patient patient) {
        userDao.update(patient);
    }


    @Override
    public Patient findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public ArrList<Patient> findAll() {
        return userDao.findAll();
    }

    @Override
    public ArrList<Patient> findAllPatientsByDoctor(String doctorName) {
        return userDao.findAllPatientsByDoctor(doctorName);
    }



}
