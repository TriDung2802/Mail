package murach.data;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBUtil {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("my_persistence_unit");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}