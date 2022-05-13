package mk.ukim.finki.wp.eLibrary.model.dto;

import lombok.Data;

@Data
public class BookDto {

    private String name;

    private Double price;

    private Integer quantity;

    private Long genre;

    private Long author;

    public BookDto() {
    }

    public BookDto(String name, Double price, Integer quantity, Long genre, Long author) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.genre = genre;
        this.author = author;
    }

}
