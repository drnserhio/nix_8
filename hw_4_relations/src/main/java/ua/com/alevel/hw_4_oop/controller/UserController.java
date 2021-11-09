package ua.com.alevel.hw_4_oop.controller;

import annotation.Autowired;
import annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.StringerUtil;
import ua.com.alevel.hw_4_oop.config.DoctorBeanList;
import ua.com.alevel.hw_4_oop.entity.Doctor;
import ua.com.alevel.hw_4_oop.entity.Patient;
import ua.com.alevel.hw_4_oop.myList.ArrList;
import ua.com.alevel.hw_4_oop.service.UserService;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ua.com.alevel.StringerUtil.exception;
import static ua.com.alevel.StringerUtil.print;

@Service
public class UserController implements Controller {

    @Autowired
    private UserService service;
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
            loggerInfo.error(e.getMessage());
        }
    }

    private void menu() {
        System.out.println(

                "\n\t\t| If you entry : |\n" +
                        " 1 - List Doctor\n" +
                        " 2 - Add Patient \n" +
                        " 3 - Drop Patient from db\n" +
                        " 4 - Update Patient from db \n" +
                        " 5 - Find user by id \n" +
                        " 6 - View all user from db\n" +
                        " 7 - Find Patients by lastname Doctor\n" +
                        " 8 - Exit"
        );
    }


    private void choose(String in, BufferedReader reader) throws IOException {

        switch (in) {

            case "1":
                getListDoctors();
                break;
            case "2":
                create(reader);
                break;
            case "3":
                drop(reader);
                break;
            case "4":
                update(reader);
                break;
            case "5":
                findById(reader);
                break;
            case "6":
                findAll();
                break;
            case "7":
                findPatientsByLastName(reader);
                break;
            case "8":
                System.exit(0);
                break;
            default:
                exception("Empty wrong...");
        }
        menu();

    }

    private void findPatientsByLastName(BufferedReader reader) throws IOException {
        print("Entry Fmiliya Doctor :");
        String doctorName = reader.readLine();
        try {
            print(service.findAllPatientsByDoctor(doctorName).toString());
        } catch (NullPointerException e) {
           exception("Patients with this doctor not found");
            loggerInfo.error(e.getMessage());
        }

    }


    public void create(BufferedReader reader) throws IOException {
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
            getListDoctors();
            print("");
            print("Choose doctors lastname in list : ");

            String findDctor = reader.readLine();
            Doctor doctor = new DoctorBeanList().get(findDctor);

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
            loggerInfo.error(e.getMessage());
        } catch (NumberFormatException e) {
            exception(e.getClass().getName());
            loggerInfo.error(e.getMessage());
        }

    }

    public void drop(BufferedReader reader) throws IOException {
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
            loggerInfo.error(e.getMessage() + " -> " + UserStateBD.USER_NOT_FOUND.name());
        } catch (NumberFormatException e) {
            exception(e.getClass().getName());
            loggerInfo.error(e.getMessage());
        }
    }

    public void update(BufferedReader reader) throws IOException {
        try {

            print("Entry id :");
            long id = Long.parseLong(reader.readLine());
            loggerInfo.info("User entry : " + id);


            print("Entry new Name user : ");
            String name = reader.readLine();
            if (StringerUtil.strIsEmpty(name)) {
                loggerInfo.error("user entry name: " + name);
                throw new NullPointerException();
            }
            loggerInfo.info("user entry name: " + name);

            print("Entry new LastName user : ");
            String lastName = reader.readLine();
            if (StringerUtil.strIsEmpty(lastName)) {
                loggerInfo.error("user entry lastname: " + name);
                throw new NullPointerException();
            }
            loggerInfo.info("user entry lastname: " + name);

            print("Entry new Age user :");
            int age = Integer.parseInt(reader.readLine());
            loggerInfo.info("User entry : " + age);

            print("Choose doctors in list:");
            getListDoctors();
            String findDoctor = reader.readLine();

            Doctor doctor = new DoctorBeanList().get(findDoctor);

            if (doctor == null) {
                loggerInfo.error("Doctor not found " + doctor);
                throw new NullPointerException();
            }
            loggerInfo.info("Doctror found " + doctor);


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
            loggerInfo.error(e.getMessage());
        } catch (NumberFormatException e) {
            exception((e.getClass().getName()));
            loggerInfo.error(e.getMessage());
        }

    }

    public void findById(BufferedReader reader) throws IOException {
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
            loggerInfo.error(e.getMessage());
        } catch (NumberFormatException e) {
            exception((e.getClass().getName()));
            loggerInfo.error(e.getMessage());
        }
    }

    public void findAll() {
      ArrList<Patient> patients = service.findAll();
      try {
          if (patients == null || patients.size() < 1) {
              loggerInfo.error("find all patients " + patients);
                throw new NullPointerException();
          }
          result("\n" + String.valueOf(service.findAll()).trim());
      } catch (NullPointerException e) {
          exception("List patients are empty");
          loggerInfo.error(e.getMessage());
      }

    }


    private void result(String str) {
        print("_____________________________________________\n"
                + "You result: " + str + "\n"
                + "_____________________________________________");
    }

    private void getListDoctors() {
        DoctorBeanList doctorBeanList = new DoctorBeanList();
        print("            *************   DOCTORS-LIST   ************* ");
        print("____________________________________________________________________");
        print(doctorBeanList.toString());
        print("____________________________________________________________________");
    }
}
