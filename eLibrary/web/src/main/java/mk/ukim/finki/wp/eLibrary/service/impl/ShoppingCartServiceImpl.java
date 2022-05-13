package mk.ukim.finki.wp.eLibrary.service.impl;

import mk.ukim.finki.wp.eLibrary.model.Book;
import mk.ukim.finki.wp.eLibrary.model.ShoppingCart;
import mk.ukim.finki.wp.eLibrary.model.User;
import mk.ukim.finki.wp.eLibrary.model.enumerations.ShoppingCartStatus;
import mk.ukim.finki.wp.eLibrary.model.exceptions.BookAlreadyInShoppingCartException;
import mk.ukim.finki.wp.eLibrary.model.exceptions.BookNotFoundException;
import mk.ukim.finki.wp.eLibrary.model.exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.wp.eLibrary.model.exceptions.UserNotFoundException;
import mk.ukim.finki.wp.eLibrary.repository.jpa.ShoppingCartRepository;
import mk.ukim.finki.wp.eLibrary.repository.jpa.UserRepository;
import mk.ukim.finki.wp.eLibrary.service.BookService;
import mk.ukim.finki.wp.eLibrary.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final BookService bookService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   UserRepository userRepository,
                                   BookService bookService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.bookService = bookService;
    }

    @Override
    public List<Book> listAllBooksInShoppingCart(Long cartId) {
        if (!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRepository.findById(cartId).get().getBooks();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.shoppingCartRepository
                .findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
    }

    @Override
    public ShoppingCart addBookToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Book book = this.bookService.findById(productId)
                .orElseThrow(() -> new BookNotFoundException(productId));
        if (shoppingCart.getBooks()
                .stream().filter(i -> i.getId().equals(productId))
                .collect(Collectors.toList()).size() > 0)
            throw new BookAlreadyInShoppingCartException(productId, username);
        shoppingCart.getBooks().add(book);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void deleteById(Long id) {
        this.shoppingCartRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.shoppingCartRepository.deleteAll();
    }

}