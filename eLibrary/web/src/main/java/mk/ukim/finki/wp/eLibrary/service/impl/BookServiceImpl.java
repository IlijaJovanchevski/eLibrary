package mk.ukim.finki.wp.eLibrary.service.impl;

import mk.ukim.finki.wp.eLibrary.model.Book;
import mk.ukim.finki.wp.eLibrary.model.Genre;
import mk.ukim.finki.wp.eLibrary.model.dto.BookDto;
import mk.ukim.finki.wp.eLibrary.model.exceptions.GenreNotFoundException;
import mk.ukim.finki.wp.eLibrary.model.Author;
import mk.ukim.finki.wp.eLibrary.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.wp.eLibrary.model.exceptions.BookNotFoundException;
import mk.ukim.finki.wp.eLibrary.repository.jpa.GenreRepository;
import mk.ukim.finki.wp.eLibrary.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.eLibrary.repository.jpa.BookRepository;
import mk.ukim.finki.wp.eLibrary.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Book> save(String name, Double price, Integer quantity, Long genreId, Long authorId) {
        Genre genre = this.genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException(genreId));
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        this.bookRepository.deleteByName(name);
        return Optional.of(this.bookRepository.save(new Book(name, price, quantity, genre, author)));
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Genre genre = this.genreRepository.findById(bookDto.getGenre())
                .orElseThrow(() -> new GenreNotFoundException(bookDto.getGenre()));
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        this.bookRepository.deleteByName(bookDto.getName());
        return Optional.of(this.bookRepository.save(new Book(bookDto.getName(), bookDto.getPrice(), bookDto.getQuantity(), genre, author)));
    }

    @Override
    @Transactional
    public Optional<Book> edit(Long id, String name, Double price, Integer quantity, Long genreId, Long authorId) {

        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setName(name);
        book.setPrice(price);
        book.setQuantity(quantity);

        Genre genre = this.genreRepository.findById(genreId)
                .orElseThrow(() -> new GenreNotFoundException(genreId));
        book.setGenre(genre);

        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        book.setAuthor(author);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setName(bookDto.getName());
        book.setPrice(bookDto.getPrice());
        book.setQuantity(bookDto.getQuantity());

        Genre genre = this.genreRepository.findById(bookDto.getGenre())
                .orElseThrow(() -> new GenreNotFoundException(bookDto.getGenre()));
        book.setGenre(genre);

        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        book.setAuthor(author);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

}
