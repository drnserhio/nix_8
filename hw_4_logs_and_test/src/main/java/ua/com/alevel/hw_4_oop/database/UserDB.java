package ua.com.alevel.hw_4_oop.database;


import ua.com.alevel.hw_4_oop.entity.Patient;
import ua.com.alevel.hw_4_oop.mylist.ArrList;

public interface UserDB extends BaseDB<Patient> {
    ArrList<Patient> findAllPatientsByDoctor(String doctorName);
}
