package vea.home;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import vea.home.entities.Customer;
import vea.home.entities.Passport;
import vea.home.utils.HibernateUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OneTOneRelationTest {


    @Test
    @Order(1)
    void successCreateTest() {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            transaction = session.beginTransaction();

            Passport passport = new Passport("1-34");
            Customer customer = new Customer("Eugen", passport);
            session.persist(customer);

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
    @Order(2)
    void successRemovalTest() {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            transaction = session.beginTransaction();

            Customer customer = session.get(Customer.class, 1L);

            assertEquals("Eugen", customer.getName());
            assertEquals("1-34", customer.getPassport().getPassportNumber());
            session.delete(customer);

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
