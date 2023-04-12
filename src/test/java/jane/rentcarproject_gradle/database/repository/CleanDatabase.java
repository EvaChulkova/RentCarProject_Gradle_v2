package jane.rentcarproject_gradle.database.repository;

import javax.persistence.EntityManager;

public class CleanDatabase {
    public static void cleanTestDatabase(EntityManager entityManager) {
        System.out.println("RUN TEST DATABASE CLEANUP");
        entityManager.createQuery("DELETE FROM Booking").executeUpdate();
        entityManager.createQuery("DELETE FROM Car").executeUpdate();
        entityManager.createQuery("DELETE FROM Client ").executeUpdate();
        entityManager.createQuery("DELETE FROM User").executeUpdate();
        System.out.println("TEST DATABASE CLEANUP COMPLETED SUCCESSFULLY");
    }
}
