package mk.ukim.finki.wp.eLibrary.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "Author_Residency")
    private String residency;

    public Author() {
    }

    public Author(String name, String residency) {
        this.name = name;
        this.residency = residency;
    }

}
