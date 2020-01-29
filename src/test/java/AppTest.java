import libary.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


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

}
