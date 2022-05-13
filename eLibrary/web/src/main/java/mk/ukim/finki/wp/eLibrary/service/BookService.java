package mk.ukim.finki.wp.eLibrary.service;

import mk.ukim.finki.wp.eLibrary.model.Book;
import mk.ukim.finki.wp.eLibrary.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> save(String name, Double price, Integer quantity, Long category, Long manufacturer);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, String name, Double price, Integer quantity, Long category, Long manufacturer);

    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);

}
