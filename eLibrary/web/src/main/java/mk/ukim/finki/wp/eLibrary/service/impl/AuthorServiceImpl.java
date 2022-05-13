package mk.ukim.finki.wp.eLibrary.service.impl;

import mk.ukim.finki.wp.eLibrary.model.Author;
import mk.ukim.finki.wp.eLibrary.model.dto.AuthorDto;
import mk.ukim.finki.wp.eLibrary.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.wp.eLibrary.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.eLibrary.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> save(String name, String address) {

        return Optional.of(this.authorRepository.save(new Author(name, address)));
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {

        this.authorRepository.deleteByName(authorDto.getName());
        return Optional.of(this.authorRepository.save(new Author(authorDto.getName(), authorDto.getResidency())));
    }

    @Override
    @Transactional
    public Optional<Author> edit(Long id, String name, String residency) {

        Author author = this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));

        author.setName(name);
        author.setResidency(residency);

        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));

        author.setName(authorDto.getName());
        author.setResidency(authorDto.getResidency());

        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }

}
