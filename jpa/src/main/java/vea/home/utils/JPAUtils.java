package vea.home.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {
    private static final Logger log = LoggerFactory.getLogger(JPAUtils.class);
    private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

    private JPAUtils() {
    }

    private static EntityManagerFactory buildEntityManagerFactory() {
        try {

            log.info("Create EntityManagerFactory");
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello-world");
            log.info("EntityManagerFactory success created");
            return entityManagerFactory;

        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
