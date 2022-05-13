package mk.ukim.finki.wp.eLibrary.web.controller;

import mk.ukim.finki.wp.eLibrary.model.Author;
import mk.ukim.finki.wp.eLibrary.service.AuthorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String getAuthors(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Author> authors = this.authorService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("bodyContent", "authors");
        return "master-template-authors";
    }

    @GetMapping("/authors/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showAddPage(Model model) {
        model.addAttribute("bodyContent", "add-author");
        return "master-template-authors";
    }

    @PostMapping("/authors")
    public String create(@RequestParam String name, @RequestParam String residency) {
        this.authorService.save(name, residency);
        return "redirect:/authors";
    }

    @GetMapping("/authors/{id}/edit")
    public String showEditPage(@PathVariable Long id, Model model) {
        if (this.authorService.findById(id).isPresent()) {
            Author author = this.authorService.findById(id).get();
            model.addAttribute("author", author);
            model.addAttribute("bodyContent", "add-author");
            return "master-template-authors";
        }
        return "redirect:/authors?error=AuthorNotFound";
    }

    @PostMapping("/authors/{id}")
    public String update(@PathVariable Long id, @RequestParam String name, @RequestParam String residency) {
        this.authorService.edit(id, name, residency);
        return "redirect:/authors";
    }

    @DeleteMapping("authors/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        this.authorService.deleteById(id);
        return "redirect:/authors";
    }

}
