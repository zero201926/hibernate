package libary;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity

@Table(name = "book")
public class Book implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;


    public String getID() {
        return id;
    }
    public void setID() {
        this.id = UUID.randomUUID().toString();
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
}