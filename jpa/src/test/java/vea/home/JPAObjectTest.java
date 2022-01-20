package vea.home;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import vea.home.entities.Guide;
import vea.home.entities.Student;
import vea.home.utils.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JPAObjectTest {


    @Test
    @Order(1)
    void persistTest() {

        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Guide guide1 = new Guide("1", "Guide1", 5500);
            Guide guide2 = new Guide("2", "Guide2", 5200);
            Guide guide3 = new Guide("3", "Guide3", 5345);

            Student student1 = new Student("1245", "Student1", guide1);
            Student student2 = new Student("1246", "Student2", guide1);
            Student student3 = new Student("1247", "Student3", null);
            Student student4 = new Student("1248", "Student4", guide2);
            Student student5 = new Student("1249", "Student5", guide3);

            entityManager.persist(student1);
            entityManager.persist(student2);
            entityManager.persist(student3);
            entityManager.persist(student4);
            entityManager.persist(student5);


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
    void findTest() {

        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            TypedQuery<Student> query = entityManager.createQuery("select student from Student student", Student.class);
            List<Student> students = query.getResultList();
            students.forEach(s-> System.out.printf("Student name= %s, enrolmentId = %s%n",s.getName(),s.getEnrollmentId()));

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
