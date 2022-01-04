package vea.home;

import org.junit.jupiter.api.Test;
import vea.home.model.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloWorldJPATest {
    @Test
    void successStoring() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloWorldPU");

        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Message message = new Message();
        message.setText("Hello world!");
        entityManager.persist(message);

        transaction.commit();
        entityManager.close();

        entityManager = emf.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();

        //noinspection unchecked
        List<Message> messages = entityManager.createQuery("select m from Message m")
                .getResultList();

        assertEquals(1, messages.size());
        assertEquals("Hello world!", messages.get(0).getText());
        messages.get(0).setText("New text");
        transaction.commit();
        entityManager.close();

    }
}
