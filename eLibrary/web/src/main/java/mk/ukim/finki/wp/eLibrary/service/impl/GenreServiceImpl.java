package mk.ukim.finki.wp.eLibrary.service.impl;

import mk.ukim.finki.wp.eLibrary.model.Genre;
import mk.ukim.finki.wp.eLibrary.model.dto.GenreDto;
import mk.ukim.finki.wp.eLibrary.model.exceptions.GenreNotFoundException;
import mk.ukim.finki.wp.eLibrary.repository.jpa.GenreRepository;
import mk.ukim.finki.wp.eLibrary.service.GenreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre create(String name, String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Genre c = new Genre(name, description);
        genreRepository.save(c);
        return c;
    }

    @Override
    public void delete(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        genreRepository.deleteByName(name);
    }

    @Override
    public List<Genre> listGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> save(String name, String description) {
        return Optional.of(this.genreRepository.save(new Genre(name, description)));
    }

    @Override
    public Optional<Genre> save(GenreDto genreDto) {

        this.genreRepository.deleteByName(genreDto.getName());
        return Optional.of(this.genreRepository.save(new Genre(genreDto.getName(), genreDto.getDescription())));
    }

    @Override
    public List<Genre> findAll() {
        return this.genreRepository.findAll();
    }


    @Override
    @Transactional
    public Optional<Genre> edit(Long id, String name, String description) {

        Genre genre = this.genreRepository.findById(id).orElseThrow(() -> new GenreNotFoundException(id));

        genre.setName(name);
        genre.setDescription(description);

        return Optional.of(this.genreRepository.save(genre));
    }

    @Override
    public Optional<Genre> edit(Long id, GenreDto genreDto) {
        Genre genre = this.genreRepository.findById(id).orElseThrow(() -> new GenreNotFoundException(id));

        genre.setName(genreDto.getName());
        genre.setDescription(genreDto.getDescription());

        return Optional.of(this.genreRepository.save(genre));
    }

    @Override
    public void deleteById(Long id) {
        this.genreRepository.deleteById(id);
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return this.genreRepository.findById(id);
    }

}
