package mk.ukim.finki.wp.eLibrary.repository.jpa;

import mk.ukim.finki.wp.eLibrary.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    void deleteByName(String name);

}
