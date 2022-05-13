package mk.ukim.finki.wp.eLibrary.service;

import mk.ukim.finki.wp.eLibrary.model.Book;
import mk.ukim.finki.wp.eLibrary.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<Book> listAllBooksInShoppingCart(Long cartId);

    ShoppingCart getActiveShoppingCart(String username);

    ShoppingCart addBookToShoppingCart(String username, Long productId);

    void deleteById(Long id);

    void deleteAll();

}
