import org.junit.jupiter.api.*;
import ua.com.alevel.hw_4_oop.entity.Patient;
import ua.com.alevel.hw_4_oop.myList.ArrList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArrListTest {

    private ArrList<Patient> patients = new ArrList<>();
    private Patient patient = new Patient(
            1L,
            "name",
            "lastname",
            34,
            "doctor");
    private Patient patient1 = null;


    @Test
    @Order(1)
    public void add() {
        patients.add(patient);
        Assertions.assertEquals(patients.size(), 1);
    }

    @Test
    @Order(2)
    public void delete() {
        patients.add(patient);
        patients.remove(patient);
        Assertions.assertEquals(patients.size(), 0);
    }
}
