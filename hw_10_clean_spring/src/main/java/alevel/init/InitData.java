package alevel.init;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class InitData {

    private final EntityManagerFactory entityManagerFactory;

    public InitData(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void init() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager
                    .createNativeQuery("insert into employees(id, firstname, lastname, username) VALUES\n" +
                            "(1,  'Ivanov', 'Ivanov', 'ianov'),\n" +
                            "(2,  'Dmitriy', 'Vasilevich', 'dmit123'),\n" +
                            "(3,  'Konstantin', 'Ivanov', 'ks1234'),\n" +
                            "(4,  'Kiril', 'Kirilov', 'kr'),\n" +
                            "(5,  'Oram', 'Hayam', 'ohayam'),\n" +
                            "(6,  'Krostopher', 'Columb', 'krio'),\n" +
                            "(7,  'Abab', 'Bab', 'Cac'),\n" +
                            "(8,  'DD', 'DD', 'DD'),\n" +
                            "(9,  'CC', 'CC', 'CC'),\n" +
                            "(10,  'TT', 'TT', 'TT'),\n" +
                            "(11,  'ZZ', 'ZZ', 'ZZ'),\n" +
                            "(12,  'VV', 'VV', 'VV'),\n" +
                            "(13,  'NN', 'NN', 'NN'),\n" +
                            "(14,  'YY', 'YY', 'CaYYc');")
                    .executeUpdate();
            transaction.commit();

            transaction.begin();
            entityManager
                    .createNativeQuery("insert into departments (id, nameCompany, address) VALUES\n" +
                            "(1, 'Nix-Solution', 'Karazina St, 2'),\n" +
                            "(2, 'Epam', 'Kolomenskaya St, 63'),\n" +
                            "(3, 'Logic', 'Novhorods''ka St, 3-B')")
                    .executeUpdate();
            transaction.commit();

            transaction.begin();
            entityManager
                    .createNativeQuery("insert into relations values (1, 1);")
                    .executeUpdate();
            entityManager
                    .createNativeQuery("insert into relations values (2, 1);")
                    .executeUpdate();
            entityManager
                    .createNativeQuery("insert into relations values (3, 4);")
                    .executeUpdate();
            entityManager
                    .createNativeQuery("insert into relations values (3, 5);")
                    .executeUpdate();
            entityManager
                    .createNativeQuery("insert into relations values (2, 3);")
                    .executeUpdate();
            entityManager
                    .createNativeQuery("insert into relations values (1, 6);")
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            entityManager.close();
            e.printStackTrace();
        }
    }
}
