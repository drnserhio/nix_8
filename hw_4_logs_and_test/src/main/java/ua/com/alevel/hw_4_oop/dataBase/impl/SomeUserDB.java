package ua.com.alevel.hw_4_oop.dataBase.impl;


import ua.com.alevel.hw_4_oop.dataBase.UserDB;
import ua.com.alevel.hw_4_oop.entity.Doctor;
import ua.com.alevel.hw_4_oop.entity.Patient;
import ua.com.alevel.hw_4_oop.myList.ArrList;

public class SomeUserDB implements UserDB {

    private ArrList<Patient> list;
    private static SomeUserDB instance;
    private static long idUser = 0;

    private SomeUserDB() {
        list = new ArrList<>();
    }

    public static SomeUserDB getInstance() {
        if (instance == null) {
            instance = new SomeUserDB();
        }
        return instance;
    }

    public void create(Patient patient) {
        patient.setId(generateId());
        list.add(patient);
    }

    @Override
    public void delete(Long id) {
        if (list.remove(id)) {
            --idUser;
        }
    }

    public void update(Patient patient) {
        Patient curr = findById(patient.getId());
        curr.setName(patient.getName());
        curr.setAge(patient.getAge());
    }

    private Long generateId() {
        return ++idUser;
    }

    public Patient findById(Long id) {
        return (Patient) list.get(id);
    }

    public ArrList<Patient> findAll() {
        return list;
    }

    public ArrList<Patient> findAllPatientsByDoctor(String doctorName) {
        return list.findAllPatientsByDoctor(doctorName);
    }


}
