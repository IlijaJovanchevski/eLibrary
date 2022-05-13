package mk.ukim.finki.wp.eLibrary.service;

import mk.ukim.finki.wp.eLibrary.model.Author;
import mk.ukim.finki.wp.eLibrary.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(Long id);

    List<Author> findAll();

    Optional<Author> save(String name, String address);

    Optional<Author> save(AuthorDto authorDto);

    void deleteById(Long id);

    Optional<Author> edit(Long id, String name, String residency);

    Optional<Author> edit(Long id, AuthorDto authorDto);
    
}
