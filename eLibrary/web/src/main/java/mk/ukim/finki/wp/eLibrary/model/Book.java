package mk.ukim.finki.wp.eLibrary.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer quantity;

    @ManyToOne
    private Genre genre;

    @ManyToOne
    private Author author;

    public Book() {
    }

    public Book(String name, Double price, Integer quantity, Genre genre, Author author) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.genre = genre;
        this.author = author;
    }

}
