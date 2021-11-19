package ua.com.alevel.hw_4_oop.dao;

import ua.com.alevel.hw_4_oop.entity.Patient;
import ua.com.alevel.hw_4_oop.mylist.ArrList;

public interface UserDao extends BaseDao<Patient> {

    ArrList<Patient> findAllPatientsByDoctor(String doctorName);
}
