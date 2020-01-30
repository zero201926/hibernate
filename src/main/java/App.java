import libary.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class App implements Database{

    @Override
    public Book enterBook(String name, String price){
        Book book = new Book();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        String uniqueID = UUID.randomUUID().toString();
        Session session = sessionFactory.openSession();
        // start transaction
        session.beginTransaction();
        try {
            book.setID();
            book.setName(name);
            book.setPrice(price);
            session.save(book);
            session.getTransaction().commit();
        } catch(Exception sqlException) {
            if(null != session.getTransaction()) {
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            session.close();
        }
        return book;
    }

    @Override
    public boolean checkIfBookInLibrary(String name) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.get(Book.class, name);
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
        } catch(Exception sqlException) {
            if(null != session.getTransaction()) {
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public List readAllBooks() {
        List<Book> libraryList = new ArrayList<>();
        List<Object> bookInfo = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
//        System.out.println(session.createQuery("FROM Book").list());
        try {
            libraryList = session.createQuery("FROM Book").list();
        } catch(Exception sqlException) {
            if(null != session.getTransaction()) {
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        for (Book book : libraryList){
            List<Object> info = new ArrayList<>();
            info.add(book.getID());
            info.add(book.getName());
            info.add(book.getPrice());
            bookInfo.add(info);
        }
//        System.out.println(libraryList);
        return bookInfo;
    }


    @Override
    public void updateData(String id, String newPrice, String newName) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            // Creating Transaction Entity
            Book stuObj = (Book) session.get(Book.class, id);
            stuObj.setName(newName);
            stuObj.setPrice(newPrice);

            // Committing The Transactions To The Database
            session.getTransaction().commit();
        } catch(Exception sqlException) {
            if(null != session.getTransaction()) {
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            session.close();
        }
    }

//    @Override
//    public void takeBook(String name) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        try {
//            // Creating Transaction Entity
//            Book book = session.get(Book.class, name);
//            session.delete(book.getID());
//            // Committing The Transactions To The Database
//            session.getTransaction().commit();
//        } catch(Exception sqlException) {
//            if(null != session.getTransaction()) {
//                session.getTransaction().rollback();
//            }
//            sqlException.printStackTrace();
//        } finally {
//            if(session != null) {
//                session.close();
//            }
//        }
//    }
}
