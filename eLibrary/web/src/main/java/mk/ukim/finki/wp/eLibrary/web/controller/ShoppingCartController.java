package mk.ukim.finki.wp.eLibrary.web.controller;

import mk.ukim.finki.wp.eLibrary.model.Book;
import mk.ukim.finki.wp.eLibrary.model.ShoppingCart;
import mk.ukim.finki.wp.eLibrary.model.User;
import mk.ukim.finki.wp.eLibrary.model.exceptions.BookNoLongerAvailableException;
import mk.ukim.finki.wp.eLibrary.model.exceptions.BookNotFoundException;
import mk.ukim.finki.wp.eLibrary.service.BookService;
import mk.ukim.finki.wp.eLibrary.service.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final BookService bookService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, BookService bookService) {
        this.shoppingCartService = shoppingCartService;
        this.bookService = bookService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username = req.getRemoteUser();
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);
        model.addAttribute("books", this.shoppingCartService.listAllBooksInShoppingCart(shoppingCart.getId()));
        model.addAttribute("bodyContent", "shopping-cart");
        return "master-template";
    }

    @PostMapping("/add-book/{id}")
    public String addBookToShoppingCart(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
        try {
            Book book = this.bookService.findById(id).orElseThrow(() -> new BookNotFoundException(id));
            int newQuantity = book.getQuantity();
            if (newQuantity >= 1) {
                newQuantity = newQuantity - 1;
                book.setQuantity(newQuantity);
            } else throw new BookNoLongerAvailableException(book.getName());
            User user = (User) authentication.getPrincipal();
            this.shoppingCartService.addBookToShoppingCart(user.getUsername(), id);
//            this.bookService.save(book.getName(), book.getPrice(), book.getQuantity(), book.getGenre().getId(), book.getAuthor().getId());
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBookFromCart(@PathVariable Long id) {
        this.shoppingCartService.deleteById(id);
        return "redirect:/shopping-cart";
    }

    @GetMapping("/reserve")
    public String reserveBooks() {
        this.shoppingCartService.deleteAll();
        return "redirect:/books";
    }

}
