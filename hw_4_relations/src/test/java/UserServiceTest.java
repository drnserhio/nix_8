import org.junit.jupiter.api.*;
import ua.com.alevel.hw_4_oop.dao.UserDao;
import ua.com.alevel.hw_4_oop.dao.impl.UserDaoImpl;
import ua.com.alevel.hw_4_oop.entity.Patient;
import ua.com.alevel.hw_4_oop.myList.ArrList;
import ua.com.alevel.hw_4_oop.service.impl.UserServiceImpl;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    private UserDao userDao = new UserDaoImpl();
    private UserServiceImpl userService = new UserServiceImpl();
    private Patient patient = new Patient(
            1L,
            "name",
            "lastname",
            34,
            "doctor");

    @Test
    @Order(1)
    public void create() {
        userDao.create(patient);
    }

    @Test
    @Order(2)
    public void delete() {
        userDao.delete(patient.getId());
    }

    @Test
    @Order(3)
    public void update() {
        userDao.create(patient);
        userDao.update(patient);
    }

    @Test
    @Order(4)
    public Patient findById() {
        return userDao.findById(patient.getId());
    }

    @Test
    @Order(5)
    public void findAll() {
        userDao.create(patient);
       Assertions.assertEquals(userDao.findAll().size(), 2);
    }

    @Test
    @Order(6)
    public ArrList<Patient> findAllPatientsByDoctor() {
        return userDao.findAllPatientsByDoctor(patient.getLastnameDoctor());
    }






}
