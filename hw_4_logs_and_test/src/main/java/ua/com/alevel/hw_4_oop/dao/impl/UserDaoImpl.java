package ua.com.alevel.hw_4_oop.dao.impl;

import annotation.Service;
import ua.com.alevel.hw_4_oop.dao.UserDao;
import ua.com.alevel.hw_4_oop.database.UserDB;
import ua.com.alevel.hw_4_oop.database.impl.DataBase;
import ua.com.alevel.hw_4_oop.entity.Patient;
import ua.com.alevel.hw_4_oop.mylist.ArrList;

@Service
public class UserDaoImpl implements UserDao {

    public void create(Patient patient) {
        DataBase.getInstance().create(patient);
    }

    public void delete(Long id) {
        DataBase.getInstance().delete(id);
    }

    public void update(Patient patient) {
        DataBase.getInstance().update(patient);
    }

    public Patient findById(Long id) {
        return DataBase.getInstance().findById(id);
    }

    public ArrList<Patient> findAll() {
        return DataBase.getInstance().findAll();
    }

    @Override
    public ArrList<Patient> findAllPatientsByDoctor(String doctorName) {
        return DataBase.getInstance().findAllPatientsByDoctor(doctorName);
    }
}
