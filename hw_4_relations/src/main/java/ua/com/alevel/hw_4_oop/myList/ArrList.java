package ua.com.alevel.hw_4_oop.myList;


import ua.com.alevel.hw_4_oop.entity.Doctor;
import ua.com.alevel.hw_4_oop.entity.Patient;

public class ArrList<T extends Patient> extends MyList {

    public boolean add(T ob) {
        return super.add(ob);
    }

    public boolean remove(T ob) {

        return super.remove(ob);
    }

    @Override
    public Object get(Object ob) {
        return super.get(ob);
    }


    @Override
    public Object get(Long id) {
        return super.get(id);
    }

    public ArrList<Patient> findAllPatientsByDoctor(String lastNameDoctor) {
        return super.findAllPatientsByDoctor(lastNameDoctor);
    }


}
