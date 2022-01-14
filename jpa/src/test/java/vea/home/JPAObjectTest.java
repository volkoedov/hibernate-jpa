package vea.home;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import vea.home.entities.Guide;
import vea.home.entities.Message;
import vea.home.entities.Student;
import vea.home.utils.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JPAObjectTest {


    @Test
    @Order(1)
    void successQuestion1Test() {

        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Message message = new Message("Hello");

            entityManager.persist(message);

            entityManager.detach(message);

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            fail("Такого быть не дожно!");
        } finally {
            entityManager.close();
        }
    }

    @Test
    @Order(2)
    void successQuestion2Test() {

        EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();

        EntityManager em1 = emf.createEntityManager();
        em1.getTransaction().begin();

        Guide guide = new Guide("2000RN10349", "Rose Ann", 4000);
        em1.persist(guide);

        em1.getTransaction().commit();
        em1.close();

        EntityManager em2 = emf.createEntityManager();
        em2.getTransaction().begin();

        Guide mergedGuide = em2.merge(guide);
        mergedGuide.addStudent(new Student("2015JR50244", "Jason Bird"));
        mergedGuide.addStudent(new Student("2015LK50878", "Lisa Mizuki"));

        em2.getTransaction().commit();
        em2.close();
    }


}
