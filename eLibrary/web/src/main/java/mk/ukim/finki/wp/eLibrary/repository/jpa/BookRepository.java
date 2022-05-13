package mk.ukim.finki.wp.eLibrary.repository.jpa;

import mk.ukim.finki.wp.eLibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    void deleteByName(String name);

}
