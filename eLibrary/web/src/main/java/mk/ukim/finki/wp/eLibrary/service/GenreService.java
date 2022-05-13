package mk.ukim.finki.wp.eLibrary.service;

import mk.ukim.finki.wp.eLibrary.model.Genre;
import mk.ukim.finki.wp.eLibrary.model.dto.GenreDto;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Genre create(String name, String description);

    List<Genre> findAll();

    void delete(String name);

    List<Genre> listGenres();

    Optional<Genre> save(String name, String description);

    Optional<Genre> save(GenreDto genreDto);

    void deleteById(Long id);

    Optional<Genre> edit(Long id, String name, String description);

    Optional<Genre> edit(Long id, GenreDto genreDto);

    Optional<Genre> findById(Long id);

}
