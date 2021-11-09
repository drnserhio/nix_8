package ua.com.alevel.hw_4_oop.service;


import ua.com.alevel.hw_4_oop.entity.Patient;
import ua.com.alevel.hw_4_oop.myList.ArrList;

public interface UserService extends BaseService<Patient> {
    ArrList<Patient> findAllPatientsByDoctor(String doctorName);
}
