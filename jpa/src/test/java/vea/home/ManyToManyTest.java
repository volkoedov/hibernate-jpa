package vea.home;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import vea.home.entities.Employee;
import vea.home.entities.EmployeeStatus;
import vea.home.utils.HibernateUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EnumeratedTest {


    @Test
    @Order(1)
    void successCreateTest() {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            transaction = session.beginTransaction();

            Employee employee1 = new Employee("1111", "Jeka", EmployeeStatus.FULL_TIME);
            Employee employee2 = new Employee("2222", "Jeka1", EmployeeStatus.PART_TIME);
            Employee employee3 = new Employee("3333", "Jeka2", EmployeeStatus.CONTRACT);

            session.persist(employee1);
            session.persist(employee2);
            session.persist(employee3);

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
    void successGetTest() {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            transaction = session.beginTransaction();

            Employee employee = session.get(Employee.class, 2L);

            assertEquals(EmployeeStatus.PART_TIME,employee.getStatus());

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
