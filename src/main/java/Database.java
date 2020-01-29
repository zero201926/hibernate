public interface Database {

    void enterBook(String name, String price);
    boolean checkIfBookInLibrary(String name);
    void deleteBook(String name);
    void insertData(String newData);
    void updateData(String id);
    void takeBook(String name);

}
