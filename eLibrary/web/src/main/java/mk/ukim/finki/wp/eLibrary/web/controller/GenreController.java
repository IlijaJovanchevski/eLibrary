package mk.ukim.finki.wp.eLibrary.web.controller;

import mk.ukim.finki.wp.eLibrary.model.Genre;
import mk.ukim.finki.wp.eLibrary.service.AuthorService;
import mk.ukim.finki.wp.eLibrary.service.BookService;
import mk.ukim.finki.wp.eLibrary.service.GenreService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public String showGenres(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Genre> genres = this.genreService.findAll();
        model.addAttribute("genres", genres);
        model.addAttribute("bodyContent", "genres");
        return "master-template-genres";
    }

    @GetMapping("/genres/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showAddPage(Model model) {
        model.addAttribute("bodyContent", "add-genre");
        return "master-template-genres";
    }

    @PostMapping("/genres")
    public String create(@RequestParam String name, @RequestParam String description) {
        this.genreService.save(name, description);
        return "redirect:/genres";
    }

    @GetMapping("/genres/{id}/edit")
    public String showEditPage(@PathVariable Long id, Model model) {
        if (this.genreService.findById(id).isPresent()) {
            Genre genre = this.genreService.findById(id).get();
            model.addAttribute("genre", genre);
            model.addAttribute("bodyContent", "add-genre");
            return "master-template-genres";
        }
        return "redirect:/genres?error=GenreNotFound";
    }

    @PostMapping("/genres/{id}")
    public String update(@PathVariable Long id, @RequestParam String name, @RequestParam String description) {

        this.genreService.edit(id, name, description);
        return "redirect:/genres";
    }

    @PostMapping("genres/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        this.genreService.deleteById(id);
        return "redirect:/genres";
    }

}
