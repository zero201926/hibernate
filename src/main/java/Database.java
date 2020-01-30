import libary.Book;

import java.util.List;

public interface Database {

    Book enterBook(String name, String price);
    boolean checkIfBookInLibrary(String name);
    void deleteBook(String name);
    List readAllBooks();
    void updateData(String id, String newPrice, String newName);
//    void takeBook(String name);

}
