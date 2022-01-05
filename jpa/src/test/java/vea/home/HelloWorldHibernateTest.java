package vea.home;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import vea.home.model.Message;
import vea.home.utils.HibernateUtil;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class HelloWorldHibernateTest {
    @Test
    void successStoring() {
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        session.beginTransaction();
        Message message = new Message("Hello world!");
        session.save(message);
        session.getTransaction().commit();
        session.close();

        assertNotNull(message.getId());

    }
}
