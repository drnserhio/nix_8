package ua.com.alevel.hw_4_oop.dataBase.impl;


import ua.com.alevel.hw_4_oop.dataBase.UserDB;
import ua.com.alevel.hw_4_oop.entity.Doctor;
import ua.com.alevel.hw_4_oop.entity.Patient;
import ua.com.alevel.hw_4_oop.myList.ArrList;

public class DataBase implements UserDB {

    private final ArrList<Patient> list;
    private static DataBase instance;
    private static long idUser = 0;

    public DataBase() {
        list = new ArrList<>();
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;

    }

    public void create(Patient Patient) {
        Patient.setId(generateId());
        list.add(Patient);
    }

    @Override
    public void delete(Long id) {
        if (list.remove(id)) {
            --idUser;
        }
    }

    @Override
    public void update(Patient Patient) {
        Patient curr = findById(Patient.getId());
        curr.setName(Patient.getName());
        curr.setAge(Patient.getAge());
    }

    private Long generateId() {
        return ++idUser;
    }

    @Override
    public Patient findById(Long id) {
        return (Patient) list.get(id);
    }

    @Override
    public ArrList<Patient> findAll() {
        return list;
    }


    public ArrList<Patient> findAllPatientsByDoctor(String doctorName) {
        return list.findAllPatientsByDoctor(doctorName);
    }

}
