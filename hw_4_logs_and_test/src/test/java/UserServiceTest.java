import org.junit.jupiter.api.*;
import ua.com.alevel.hw_4_oop.dao.UserDao;
import ua.com.alevel.hw_4_oop.dao.impl.UserDaoImpl;
import ua.com.alevel.hw_4_oop.entity.Patient;
import ua.com.alevel.hw_4_oop.myList.ArrList;
import ua.com.alevel.hw_4_oop.service.impl.UserServiceImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    private UserServiceImpl userService = new UserServiceImpl();
    private Patient patient = new Patient(
            1L,
            "name",
            "lastname",
            34,
            "doctor");
    private Patient patientNullable = null;
    private Patient patientEmpty = new Patient();


    @Test
    @Order(1)
    public void create() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> userService.findById(patient.getId()),
                "list is null ");

        Assertions.assertNull(patientNullable);
        Assertions.assertNull(patientEmpty.getLastname());
        Assertions.assertNull(patientEmpty.getId());
        Assertions.assertNull(patientEmpty.getName());
    }

    @Test
    @Order(2)
    public void delete() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> userService.delete(patient.getId()));
    }

    @Test
    @Order(3)
    public void update() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> userService.findById(patient.getId()),
                "User not found");

        Assertions.assertThrows(
                NullPointerException.class,
                () -> userService.update(patient),
                "user not found in list");

        Assertions.assertThrows(
                NullPointerException.class,
                () -> userService.update(patient),
                "user nullable");


    }

    @Test
    @Order(4)
    public void findById() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> userService.findById(patient.getId()),
                "user not found");

        Assertions.assertThrows(
                NullPointerException.class,
                () -> userService.findById(patient.getId()),
                "user nullable");

    }

    @Test
    @Order(5)
    public void findAll() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> userService.findAll().size(),
                "List user is null");
    }

    @Test
    @Order(6)
    public void findAllPatientsByDoctor() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> userService.findAllPatientsByDoctor(patient.getLastnameDoctor()),
                "List doctors is null");
    }






}
