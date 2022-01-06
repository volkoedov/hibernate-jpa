package vea.home;

import org.hibernate.Session;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import vea.home.entities.Message;
import vea.home.utils.HibernateUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ManipulatingObjectTest {
    @Test
    @Order(1)
    void manipulatingTest() {
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        session.beginTransaction();

        Message message = new Message("Hello");
        session.save(message);

        session.getTransaction().commit();
        session.close();

        message.setText("Hi");
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Message message2 = session.get(Message.class, 1L);

        session.merge(message);

        session.getTransaction().commit();
        session.close();
    }

}
