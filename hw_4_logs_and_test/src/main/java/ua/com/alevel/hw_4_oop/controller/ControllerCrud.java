package ua.com.alevel.hw_4_oop.controller;

import annotation.Autowired;
import annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.StringerUtil;
import ua.com.alevel.hw_4_oop.entity.Doctor;
import ua.com.alevel.hw_4_oop.entity.Patient;
import ua.com.alevel.hw_4_oop.enumeration.DoctorStateBD;
import ua.com.alevel.hw_4_oop.enumeration.UserStateBD;
import ua.com.alevel.hw_4_oop.mylist.ArrList;
import ua.com.alevel.hw_4_oop.mylist.DoctorList;
import ua.com.alevel.hw_4_oop.service.DocService;
import ua.com.alevel.hw_4_oop.service.UserService;
import ua.com.alevel.hw_4_oop.service.impl.DoctorService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

import static ua.com.alevel.StringerUtil.exception;
import static ua.com.alevel.StringerUtil.print;

@Service
public class ControllerCrud implements Controller {

    @Autowired
    private UserService service;
    private DocService serviceDoctor = new DoctorService();
    private Logger loggerInfo = LoggerFactory.getLogger("info");
    private Logger loggerError = LoggerFactory.getLogger("error");
    private Logger loggerFatal = LoggerFactory.getLogger("fatal");

    public void run() {
        menu();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String in = "";
        try {
            while ((in = reader.readLine()) != null) {
                choose(in, reader);
            }

        } catch (IOException e) {
            e.printStackTrace();
            loggerError.error(e.getMessage());
        }
    }

    private void menu() {
        System.out.println(

                "\n\t\t| If you entry : |\n" +
                        "------------------------\n" +
                        " 1 - Add Doctor\n" +
                        " 2 - Delete Doctor\n" +
                        " 3 - Update Doctor\n" +
                        " 4 - Find Doctor by id\n" +
                        " 5 - List Doctor\n" +
                        "------------------------\n" +
                        " 6 - Add Patient \n" +
                        " 7 - Delete Patient\n" +
                        " 8 - Update Patient \n" +
                        " 9 - Find Patient by id \n" +
                        " 10 - View Patient \n" +
                        " 11 - Find Patients by lastname Doctor\n" +
                        " 12 - Exit\n" +
                        "------------------------"
        );
    }


    private void choose(String in, BufferedReader reader) throws IOException {

        switch (in) {

            case "1":
                createDoctor(reader);
                break;
            case "2":
                deleteDoctor(reader);
                break;
            case "3":
                updateDoctor(reader);
                break;
            case "4":
                findDoctorById(reader);
            case "5":
                findAllDoctor();
                break;
            case "6":
                create(reader);
                break;
            case "7":
                drop(reader);
                break;
            case "8":
                update(reader);
                break;
            case "9":
                findById(reader);
                break;
            case "10":
                findAll();
                break;
            case "11":
                findPatientsByLastName(reader);
                break;
            case "12":
                System.exit(0);
                break;
            default:
                exception("Empty wrong...");
        }
        menu();

    }

    private void findDoctorById(BufferedReader reader) throws IOException {

        if (!isDoctorsHaveInList()) {
            loggerError.warn("Doctor list empty : Method findDoctorById");
            return;
        }

        try {
            print("Entry id doctor");
            long id = Long.parseLong(reader.readLine());

            Doctor doctor = serviceDoctor.findById(id);
            if (doctor == null) {
                loggerError.error("Null doctor");
                throw new NullPointerException();
            } else {
                result(String.valueOf(doctor).trim());
            }
        } catch (NullPointerException e) {
            exception((e.getClass().getName() + " -> " + DoctorStateBD.DOCTOR_NOT_FOUND.name()));
            loggerFatal.error(e.getMessage());
        } catch (NumberFormatException e) {
            exception((e.getClass().getName()));
            loggerFatal.error(e.getMessage());
        }
    }

    private void updateDoctor(BufferedReader reader) throws IOException {
        if (!isDoctorsHaveInList()) {
            doctorsListIsEmpty();
            loggerInfo.warn("Doctor list empty : Method updateDoctor");
            return;
        }

        try {

            print("Entry id Doctor:");
            long id = Long.parseLong(reader.readLine());
            loggerInfo.info("Doctor entry : " + id);

            print("Entry new Name doctor : ");
            String name = reader.readLine();
            if (StringerUtil.strIsEmpty(name)) {
                loggerError.error("doctor entry name: " + name);
                throw new NullPointerException();
            }
            loggerInfo.info("doctrod entry name: " + name);

            print("Entry new LastName doctor : ");
            String lastName = reader.readLine();
            if (StringerUtil.strIsEmpty(lastName)) {
                loggerError.error("doctor entry lastname: " + name);
                throw new NullPointerException();
            }
            loggerInfo.info("doctor entry lastname: " + name);

            print("Entry new Age doctor :");
            int age = Integer.parseInt(reader.readLine());
            loggerInfo.info("Doctor entry : " + age);


            Doctor doctor = new Doctor();
            doctor.setId(id);
            doctor.setName(name.trim());
            doctor.setLastname(lastName.trim());
            doctor.setAge(age);
            serviceDoctor.update(doctor);
            result(DoctorStateBD.DOCTOR_UPDATE.name());
            loggerInfo.info(DoctorStateBD.DOCTOR_UPDATE.name() + " " + doctor);

        } catch (NullPointerException e) {
            exception((e.getClass().getName() + " -> " + DoctorStateBD.DOCTOR_NOT_FOUND));
            loggerFatal.error(e.getMessage());
        } catch (NumberFormatException e) {
            exception((e.getClass().getName()));
            loggerFatal.error(e.getMessage());
        }
    }

    private void deleteDoctor(BufferedReader reader) throws IOException {

        if (!isDoctorsHaveInList()) {
            doctorsListIsEmpty();
            loggerInfo.warn("Doctor list empty : Method deleteDoctor");
            return;
        }

        try {
            print("Entry id doctor :");
            long id = Long.parseLong(reader.readLine());
            if (serviceDoctor.findById(id) == null) {
                throw new NullPointerException();
            }
            deleteDoctorInTwoDataBase(serviceDoctor.findById(id));
            serviceDoctor.delete(id);
            result(DoctorStateBD.DOCTOR_DROP.name());
            loggerInfo.info(DoctorStateBD.DOCTOR_DROP.name() + " " + id);
        } catch (NullPointerException e) {
            exception(e.getClass().getName() + " -> " + DoctorStateBD.DOCTOR_NOT_FOUND.name());
            loggerFatal.error("Method deleteDoctor");
            loggerFatal.error(e.getMessage() + " -> " + DoctorStateBD.DOCTOR_NOT_FOUND.name());
        } catch (NumberFormatException e) {
            exception(e.getClass().getName());
            loggerFatal.error("Method deleteDoctor");
            loggerFatal.error(e.getMessage());
        }
    }

    private void createDoctor(BufferedReader reader) throws IOException {
        try {
            print("Entry name doctor: ");
            String name = reader.readLine();
            if (StringerUtil.strIsEmpty(name)) {
                throw new NullPointerException();
            }
            print("Entry lastname doctor: ");
            String lastname = reader.readLine();
            if (StringerUtil.strIsEmpty(lastname)) {
                throw new NullPointerException();
            }
            print("Entry age doctor :");
            int age = Integer.parseInt(reader.readLine());

            Doctor doctor = new Doctor();
            doctor.setName(name.trim());
            doctor.setLastname(lastname);
            doctor.setAge(age);

            serviceDoctor.create(doctor);

            result(DoctorStateBD.DOCTOR_CREATE.name() + " -> " + serviceDoctor.findById(doctor.getId()));
            loggerInfo.info(DoctorStateBD.DOCTOR_CREATE.name() + " -> " + serviceDoctor.findById(doctor.getId()));
        } catch (NullPointerException e) {
            exception(e.getClass().getName());
            loggerFatal.error("Method createDoctor");
            loggerFatal.error(e.getMessage());
        } catch (NumberFormatException e) {
            exception(e.getClass().getName());
            loggerFatal.error("Method createDoctor");
            loggerFatal.error(e.getMessage());
        }
    }

    private void findPatientsByLastName(BufferedReader reader) throws IOException {
        if (!isPatientsHaveInList()) {
            patientsListIsEmpty();
            loggerInfo.warn("Patients list empty : Method findPatientsByLastName");
            return;
        }
        print("Entry LastName Doctor :");
        String doctorName = reader.readLine();
        try {
            print(service.findAllPatientsByDoctor(doctorName).toString());
        } catch (NullPointerException e) {
            exception("Patients with this doctor not found");
            loggerFatal.error(e.getMessage());
        }

    }

    public void create(BufferedReader reader) throws IOException {
        if (!isDoctorsHaveInList()) {
            doctorsListIsEmpty();
            loggerInfo.warn("Doctors list empty : Method create");
            return;
        }

        try {
            print("Entry name :");
            String name = reader.readLine();
            if (StringerUtil.strIsEmpty(name)) {
                throw new NullPointerException();
            }

            print("Entry lastname :");
            String lastname = reader.readLine();
            if (StringerUtil.strIsEmpty(lastname)) {
                throw new NullPointerException();
            }


            print("Entry age :");
            int age = Integer.parseInt(reader.readLine());
            findAllDoctor();
            print("");
            print("Choose doctors lastname in list : ");

            String findDctor = reader.readLine();
            Doctor doctor = serviceDoctor.findByName(findDctor)
                    .orElseThrow(() -> new NullPointerException("Doctor not found"));

            if (doctor == null) {
                throw new NullPointerException();
            }
            Patient patient = new Patient();
            patient.setName(name.trim());
            patient.setLastname(lastname);
            patient.setAge(age);
            patient.setLastnameDoctor(doctor.getLastname());

            service.create(patient);

            result(UserStateBD.USER_CREATE.name() + " -> " + service.findById(patient.getId()));
            loggerInfo.info(UserStateBD.USER_CREATE.name() + " -> " + service.findById(patient.getId()));
        } catch (NullPointerException e) {
            exception(e.getClass().getName());
            loggerFatal.error(e.getMessage());
        } catch (NumberFormatException e) {
            exception(e.getClass().getName());
            loggerFatal.error(e.getMessage());
        }

    }

    public void drop(BufferedReader reader) throws IOException {
        if (!isPatientsHaveInList()) {
            patientsListIsEmpty();
            loggerInfo.warn("Patients list empty : Method drop");
            return;
        }
        try {
            print("Entry id user :");
            long id = Long.parseLong(reader.readLine());
            if (service.findById(id) == null) {
                throw new NullPointerException();
            }
            service.delete(id);
            result(UserStateBD.USER_DROP.name());
            loggerInfo.info(UserStateBD.USER_DROP.name());
        } catch (NullPointerException e) {
            exception(e.getClass().getName() + " -> " + UserStateBD.USER_NOT_FOUND.name());
            loggerFatal.error("Method drop");
            loggerFatal.error(e.getMessage() + " -> " + UserStateBD.USER_NOT_FOUND.name());
        } catch (NumberFormatException e) {
            exception(e.getClass().getName());
            loggerFatal.error("Method drop");
            loggerFatal.error(e.getMessage());
        }
    }

    public void update(BufferedReader reader) throws IOException {
        if (!isPatientsHaveInList()) {
            patientsListIsEmpty();
            loggerInfo.warn("Patients list empty : Method update");
            return;
        }
        if (!isDoctorsHaveInList()) {
            doctorsListIsEmpty();
            loggerInfo.warn("Doctors list empty : Method update");
            return;
        }
        try {

            print("Entry id :");
            long id = Long.parseLong(reader.readLine());
            loggerInfo.info("User entry : " + id);


            print("Entry new Name user : ");
            String name = reader.readLine();
            if (StringerUtil.strIsEmpty(name)) {
                loggerFatal.error("user entry name: " + name);
                throw new NullPointerException();
            }
            loggerInfo.info("user entry name: " + name);

            print("Entry new LastName user : ");
            String lastName = reader.readLine();
            if (StringerUtil.strIsEmpty(lastName)) {
                loggerFatal.error("user entry empty lastname: " + name);
                throw new NullPointerException();
            }
            loggerInfo.info("user entry lastname: " + name);

            print("Entry new Age user :");
            int age = Integer.parseInt(reader.readLine());
            loggerInfo.info("User entry : " + age);

            print("Choose doctors in list:");
            findAllDoctor();
            String findDoctor = reader.readLine();

            Doctor doctor = serviceDoctor.findByName(findDoctor)
                    .orElseThrow(() -> new NullPointerException("Doctor not found"));

            if (doctor == null) {
                loggerFatal.error("Doctor not found " + doctor);
                throw new NullPointerException();
            }


            Patient updatePatient = new Patient();
            updatePatient.setId(id);
            updatePatient.setName(name.trim());
            updatePatient.setLastname(lastName.trim());
            updatePatient.setAge(age);
            updatePatient.setLastnameDoctor(doctor.getLastname());
            service.update(updatePatient);
            result(UserStateBD.USER_UPDATE.name());
            loggerInfo.info(UserStateBD.USER_UPDATE.name() + " " + updatePatient);

        } catch (NullPointerException e) {
            exception((e.getClass().getName() + " -> " + UserStateBD.USER_NOT_FOUND.name()));
            loggerFatal.error(e.getMessage());
        } catch (NumberFormatException e) {
            exception((e.getClass().getName()));
            loggerFatal.error(e.getMessage());
        }
    }

    public void findById(BufferedReader reader) throws IOException {
        if (!isPatientsHaveInList()) {
            patientsListIsEmpty();
            loggerInfo.warn("Patients list empty : Method findById");
            return;
        }
        try {
            print("Entry id user");
            long id = Long.parseLong(reader.readLine());

            Patient patient = service.findById(id);
            if (patient == null) {
                throw new NullPointerException();
            } else {
                result(String.valueOf(patient).trim());
            }
        } catch (NullPointerException e) {
            exception((e.getClass().getName() + " -> " + UserStateBD.USER_NOT_FOUND.name()));
            loggerFatal.error(e.getMessage());
        } catch (NumberFormatException e) {
            exception((e.getClass().getName()));
            loggerFatal.error(e.getMessage());
        }
    }

    public void findAll() {
        ArrList<Patient> patients = service.findAll();
        try {
            if (patients == null || patients.size() < 1 || patients.isEmpty()) {
                patientsListIsEmpty();
                throw new NullPointerException();
            }
            result("\n" + String.valueOf(service.findAll()).trim());
        } catch (NullPointerException e) {
            loggerInfo.error(e.getMessage());
        }

    }


    private void result(String str) {
        print("_____________________________________________\n"
                + "You result: " + str + "\n"
                + "_____________________________________________");
    }


    public void findAllDoctor() {
        DoctorList doctorList = serviceDoctor.findAll()
                .orElseThrow(() -> new NullPointerException("List doctors empty"));
        try {

            if (doctorList.isEmpty() || doctorList.size() < 1 || doctorList == null) {
                doctorsListIsEmpty();
                return;
            }
            result("\n" + String.valueOf(doctorList).trim());
        } catch (NullPointerException e) {
            exception("List doctors are empty");
            loggerFatal.error(e.getMessage());
        }
    }

    private void doctorsListIsEmpty() {
        print("_____________________________________________\n" +
                "                     *********Warning********\n"
                + "Error, doctors don't have in list , Please Create at least one doctor \n"
                + "_____________________________________________");
    }

    private boolean isDoctorsHaveInList() {
        Optional<DoctorList> doctorList = serviceDoctor.findAll();
        if (doctorList.isEmpty() || doctorList.get().size() < 1 || doctorList == null) {
            return false;
        }
        return true;
    }

    private void patientsListIsEmpty() {
        print("_____________________________________________\n" +
                "                     *********Warning********\n"
                + "Error, patients don't have in list , Please Create at least one patients \n"
                + "_____________________________________________");
    }

    private boolean isPatientsHaveInList() {
        ArrList<Patient> patients = service.findAll();
        if (patients.isEmpty() || patients.size() < 1 || patients == null) {
            return false;
        }
        return true;
    }

    private void deleteDoctorInTwoDataBase(Doctor doctor) {
        ArrList<Patient> patients = service.findAll();
        while (patients.hasNext()) {
            Patient patient = patients.next();
            if (patient.getLastnameDoctor().equals(doctor.getLastname())) {
                patient.setLastnameDoctor("empty");
                service.update(patient);
            }
        }
    }
}
