package vea.home;


import com.mchange.util.AssertException;
import org.hibernate.Session;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import vea.home.entities.Guide;
import vea.home.entities.Student;
import vea.home.utils.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Set;

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

            entityManager.persist(guide1);
            entityManager.persist(guide2);
            entityManager.persist(guide3);


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
        Guide guide=null;
        Student student=null;
        try {
            transaction.begin();

            guide = entityManager.find(Guide.class, 1L);
            Set<Student> students = guide.getStudents();
            student = students.stream()
                    .filter(s -> s.getId() == 4L)
                    .findFirst()
                    .orElseThrow(() -> new AssertException("Такого быть не должно!"));

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

        guide.setSalary(2500);
        student.setName("Amy Jade Gill");

        entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();
        transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            entityManager.merge(guide);

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
