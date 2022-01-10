package vea.home;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import vea.home.entities.Actor;
import vea.home.entities.Movie;
import vea.home.utils.HibernateUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ManyToManyTest {


    @Test
    @Order(1)
    void successCreateTest() {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            transaction = session.beginTransaction();

            Movie movie1 = new Movie("American Hustle");
            Movie movie2 = new Movie("The Prestige");

            Actor actor1 = new Actor("Christian Bale");
            Actor actor2 = new Actor("High Jackman");

            movie1.getActors().add(actor1);

            movie2.getActors().add(actor1);
            movie2.getActors().add(actor2);

            session.persist(movie1);
            session.persist(movie2);

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
    void successUpdateTest() {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            transaction = session.beginTransaction();

            Movie movie = session.get(Movie.class, 1L);
            Actor actor = session.get(Actor.class, 4L);

            actor.addMovie(movie);

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
    @Order(3)
    void successRemoveTest() {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            transaction = session.beginTransaction();

            Movie movie = session.get(Movie.class, 1L);
            Actor actor = session.get(Actor.class, 4L);

            actor.removeMovie(movie);

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
