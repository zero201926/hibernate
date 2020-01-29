import libary.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.UUID;


public class App implements Database{


    public static void main(String[] args) {


    }

    @Override
    public void enterBook(String name, String price){
        Book book = new Book();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        String uniqueID = UUID.randomUUID().toString();
        Session session = sessionFactory.openSession();

        // start transaction
        session.beginTransaction();

        book.setID(uniqueID);
        book.setName(name);
        book.setPrice(price);
        session.save(book);

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public boolean checkIfBookInLibrary(String name) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Book book = session.get(Book.class, name);
        } catch (Exception e) {
            return false;
        }

        session.close();
        return true;
    }

    @Override
    public void deleteBook(String name) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            Book book = session.get(Book.class, name);
            session.delete(book);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public void insertData(String newData) {

    }

    @Override
    public void updateData(String id) {

    }

    @Override
    public void takeBook(String name) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Book book = session.get(Book.class, name);
        session.delete(book.getID());
        session.close();
    }
}
