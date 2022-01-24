package vea.home;


import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import vea.home.entities.Guide;
import vea.home.entities.Student;
import vea.home.utils.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
            JPAUtils.getEntityManagerFactory().getCache().evictAll();
            entityManager.close();
        }
    }

    @Test
    @Order(2)
    void findTest() {
        Statistics statistics = JPAUtils.getEntityManagerFactory().unwrap(SessionFactory.class).getStatistics();
        EntityManager entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();


        try {
            transaction.begin();

            Guide guide = entityManager.find(Guide.class, 1L);
            int size = guide.getStudents().size();
            System.out.println(size);

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


        entityManager = JPAUtils.getEntityManagerFactory().createEntityManager();
        transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Guide guide = entityManager.find(Guide.class, 1L);
            int size = guide.getStudents().size();
            System.out.println(size);

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
        System.out.println("Guide: " + statistics.getCacheRegionStatistics("vea.home.entities.Guide"));
        System.out.println("Student: " + statistics.getCacheRegionStatistics("vea.home.entities.Student"));
    }


}
