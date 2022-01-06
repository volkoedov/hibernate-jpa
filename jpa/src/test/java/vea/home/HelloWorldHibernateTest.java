package vea.home;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import vea.home.entities.Message;
import vea.home.utils.HibernateUtil;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HelloWorldHibernateTest {
    @Test
    @Order(1)
    void successStoringTest() {
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        session.beginTransaction();
        Message message = new Message("Hello world!");

        session.save(message);

        session.getTransaction().commit();
        session.close();

        assertNotNull(message.getId());

    }

    @Test
    @Order(2)
    void successFetchTest() {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Message message = session.get(Message.class, 1L);
            assertEquals("Hello world!",message.getText());
            message.setText("Hello Automatic dirty checks");
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    void successDeleteTest() {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Message message = session.get(Message.class, 1L);

            session.delete(message);

            message = session.get(Message.class, 1L);

            assertNull(message);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
