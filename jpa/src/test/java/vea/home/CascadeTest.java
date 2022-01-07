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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CascadeTest {


    @Test
    @Order(1)
    void successStoringTest() {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        //noinspection TryFinallyCanBeTryWithResources
        try  {
            transaction = session.beginTransaction();

            Guide guide=new Guide("1","Dr. Volkoedov", 5000);
            Student student = new Student("1", "Eugene", guide);
            Student student2 = new Student("1", "Eugene #2", guide);

            session.persist(student);
            session.persist(student2);

            List<Student> students = session.createQuery("select s from Student s where s.guide=:guide", Student.class)
                    .setParameter("guide", student.getGuide())
                    .getResultList();

            assertEquals(2, students.size());
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }finally {
            if (session!=null){
                session.close();
            }
        }
    }


}
