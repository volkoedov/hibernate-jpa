package vea.home;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import vea.home.entities.Animal;
import vea.home.entities.Cat;
import vea.home.entities.Dog;
import vea.home.utils.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
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

            Cat cat = new Cat();
            cat.setName("Lucy");
            cat.setFluffy(Boolean.TRUE);

            Dog dog = new Dog();
            dog.setName("Oliver");

            entityManager.persist(cat);
            entityManager.persist(dog);

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

            TypedQuery<Animal> query1 = entityManager.createQuery("select animal from Animal as animal", Animal.class);
            List<Animal> animals = query1.getResultList();

            animals.forEach(System.out::println);
            animals.forEach(a -> System.out.println(a.makeNoise()));
            assertEquals(2, animals.size());

            TypedQuery<Dog> query2 = entityManager.createQuery("select dog from Dog as dog", Dog.class);
            List<Dog> dogs = query2.getResultList();

            dogs.forEach(System.out::println);
            dogs.forEach(a -> System.out.println(a.makeNoise()));
            assertEquals(1, dogs.size());

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
