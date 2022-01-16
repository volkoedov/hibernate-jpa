package vea.home;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import vea.home.entities.Message;
import vea.home.utils.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JPAObjectTest {


    @Test
    @Order(1)
    void successPersistTest() {

        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Message message = new Message("Hello");

            entityManager.persist(message);

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
    void successQuestion1Test() {

        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Message message = entityManager.find(Message.class, 1L);

            message = entityManager.merge(message);
            entityManager.detach(message);
            entityManager.remove(message);
            message = entityManager.merge(message);
            System.out.println(entityManager.contains(message));

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


}
