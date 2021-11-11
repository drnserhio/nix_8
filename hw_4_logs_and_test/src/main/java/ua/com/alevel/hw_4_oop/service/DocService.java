package ua.com.alevel.hw_4_oop.service;

import ua.com.alevel.hw_4_oop.entity.Doctor;

import java.util.Optional;

public interface DocService extends BaseDoctorService<Doctor>{
    Optional<Doctor> findByName(String name);
}
