import libary.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AppTest {
    Database model =  new App();
    String uniqueID = UUID.randomUUID().toString();
    @Before
    void setUp(){
        model.enterBook("name", "price");
    }

    @Test
    void dataIsEnteredIntoDatabase(){
        model.enterBook("harry potter", "£5");
        assertTrue(model.checkIfBookInLibrary("harry potter"));
    }

    @Test
    void dataIsDeleted(){
        model.deleteBook("price");
        assertTrue(model.checkIfBookInLibrary("name"));
    }

    @Test
    void canGetAllBooks(){
        for (int i = 0; i < 5; i++){
            model.enterBook(""+ i + "", ""+ i + "");

        }
        Assert.assertEquals(6, model.readAllBooks().size());
    }

    @Test
    void fieldIsUpdated(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Book book = model.enterBook("mock name", "mock price");
        model.updateData(book.getID(),  "£100", "Java Code Geek");
        Session session = sessionFactory.openSession();
//        System.out.println(session.createQuery("SELECT name FROM Book WHERE id = :id").setParameter("id", book.getID()).list());
        assertEquals(session.createQuery("SELECT name FROM Book WHERE id= :id").setParameter("id", book.getID())
                .list().toString().replaceAll("\\[", "").replaceAll("\\]",""), "Java Code Geek");
        session.close();
    }

}
