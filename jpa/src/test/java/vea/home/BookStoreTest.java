package vea.home;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import vea.home.entities.Book;
import vea.home.entities.Chapter;
import vea.home.entities.ChapterCompositeId;
import vea.home.entities.Publisher;
import vea.home.utils.HibernateUtil;

import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookStoreTest {


    @Test
    @Order(1)
    void successCreateTest() {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            transaction = session.beginTransaction();


            Publisher publisher = new Publisher("123", "Volkoedov Publisher Ltd");
            Book book = new Book("1", "Book about Me", publisher);

            ChapterCompositeId id1 = new ChapterCompositeId("1");
            Chapter chapter1 = new Chapter(id1,"Глава1. Начало");
            book.addChapter(chapter1);

            ChapterCompositeId id2 = new ChapterCompositeId("2");
            Chapter chapter2 = new Chapter(id2, "Глава2. Конец");
            book.addChapter(chapter2);

            session.persist(book);

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
    void successReadTest() {

        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            transaction = session.beginTransaction();


            Book book = session.get(Book.class, "1");
            System.out.println(book);

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
