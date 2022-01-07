package vea.home;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import vea.home.entities.Guide;
import vea.home.entities.Student;
import vea.home.utils.HibernateUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CascadeTest {


    @Test
    @Order(1)
    void successStoringTest() {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Guide guide = new Guide("1", "Jeka", 5000);
            Student student = new Student("1", "Eugene", guide);
            session.persist(student);

            student = new Student("2", "Olusha", guide);
            session.persist(student);

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
    void addStudentsToGuideTest() {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Guide guide = session.get(Guide.class, 2L);
            Student student1 = session.get(Student.class, 1L);

            Student student2 = new Student("1", "Eugene", null);

            guide.addStudent(student2);

            session.persist(guide);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void  successDeleteTest(){

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Student student = session.get(Student.class, 1L);

            student.setGuide(null);
            session.delete(student);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    void removeStudentFromGuideTest() {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Guide guide = session.get(Guide.class, 2L);
            Student student = session.get(Student.class, 3L);

            guide.removeStudent(student);
            session.delete(student);

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


}
