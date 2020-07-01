package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static EntityManagerFactory emf = null;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("KmFeesPers");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EntityManager getEm() {
        return emf.createEntityManager();
    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }
}
