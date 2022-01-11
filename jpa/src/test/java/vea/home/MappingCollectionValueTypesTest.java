package vea.home;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import vea.home.entities.Address;
import vea.home.entities.Friend;
import vea.home.utils.HibernateUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MappingCollectionValueTypesTest {


    @Test
    @Order(1)
    void successCreateTest() {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            transaction = session.beginTransaction();

            Friend friend = new Friend("Eugen", "volkoedov@gmail.com");

            friend.getNickNames().add("Jonny");
            friend.getNickNames().add("Dwolf");
            friend.getNickNames().add("Evgeniy");

            friend.getAddresses().add(new Address("11", "Elm St. 1", "New York"));
            friend.getAddresses().add(new Address("11", "Elm St. 3", "New York"));

            session.persist(friend);

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            fail("Такого быть не дожно!");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Test
    @Order(1)
    void successRetrievingTest() {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            transaction = session.beginTransaction();
            Friend friend = session.get(Friend.class, 1L);

            System.out.println(friend);
            assertEquals(3, friend.getNickNames().size());
            assertEquals(2, friend.getAddresses().size());


            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            fail("Такого быть не дожно!");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


}
