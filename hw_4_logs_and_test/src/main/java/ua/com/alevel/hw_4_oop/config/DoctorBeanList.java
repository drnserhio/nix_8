package ua.com.alevel.hw_4_oop.config;

import org.apache.commons.lang3.ArrayUtils;
import ua.com.alevel.hw_4_oop.entity.Doctor;
import ua.com.alevel.hw_4_oop.entity.Patient;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class DoctorBeanList {

    private Doctor[] doctors = new Doctor[1];
    int size = 0;
    private int positionIter = 0;

    public DoctorBeanList() {
        addDoctors();
    }

    private void addDoctors() {
        add(new Doctor(1L,
                "Ivanov",
                "Ivan",
                44,
                "doctor123@gmail.com")
        );
        add(new Doctor(2L,
                "Dmitrov",
                "Vladimir",
                35,
                "dmitrov@gmail.com")
        );
        add(new Doctor(3L,
                "Emaliyanov",
                "Iluya",
                27,
                "emaliyanov27iluya@gmail.com")
        );

    }

    public Doctor get(int index) {
        if (index >= doctors.length) {
            throw new IndexOutOfBoundsException();
        }
        return doctors[index];
    }

    public Doctor get(String lastname) {
        for (int i = 0; i < doctors.length; i++) {
            if (doctors[i].getLastname().equals(lastname)) {
                return get(i);
            }
        }
        return null;
    }


    protected void increaseSizeArr() {
        doctors = Arrays.copyOf(doctors, doctors.length * 2);

    }


    public boolean add(Object ob) {
        if (doctors.length == size) {
            increaseSizeArr();
        }
        doctors[size++] = (Doctor) ob;
        return true;
    }


    private boolean remove(int index) {
        doctors[index] = null;
        --size;
        reductionSizeArr(index);
        return true;
    }


    public boolean remove(Long id) {

        for (int i = 0; i < doctors.length; i++) {
            if (doctors[i].getId().equals(id)) {
                return remove(i);
            }
        }
        return false;
    }

    public boolean remove(Object ob) {
        for (int i = 0; i < doctors.length; i++) {
            if (doctors[i].equals(ob)) {
                return remove(i);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        findAndDropNull(doctors);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < doctors.length; i++) {
            stringBuilder.append(doctors[i]).append("\n");
        }
        return stringBuilder.toString();
    }


    private void reductionSizeArr(int indexDrop) {
        var first = Arrays.copyOfRange(doctors, 0, indexDrop);
        var second = Arrays.copyOfRange(doctors, indexDrop + 1, doctors.length);
        doctors = (Doctor[]) ArrayUtils.addAll(first, second);
    }

    private void findAndDropNull(Doctor[] objects) {
        for (int i = 0; i < doctors.length; i++) {
            if (doctors.length <= 1) {
                return;
            }
            if (doctors[i] == null) {
                reductionSizeArr(i);
                findAndDropNull(doctors);
            }
        }
    }

    public boolean isEmpty() {
        if (doctors.length < 1 || doctors == null) {
            return true;
        } else {
            return false;
        }
    }


    protected boolean hasNext() {
        return positionIter < doctors.length;
    }

    public Object next() {
        findAndDropNull(doctors);
        if (hasNext()) {
            return doctors[positionIter++];
        } else {
            throw new NoSuchElementException();
        }
    }


}
