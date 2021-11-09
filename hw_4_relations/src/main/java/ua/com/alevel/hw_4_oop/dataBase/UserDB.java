package ua.com.alevel.hw_4_oop.dataBase;


import ua.com.alevel.hw_4_oop.entity.Doctor;
import ua.com.alevel.hw_4_oop.entity.Patient;
import ua.com.alevel.hw_4_oop.myList.ArrList;

public interface UserDB extends BaseDB<Patient> {
    ArrList<Patient> findAllPatientsByDoctor(String doctorName);
}
