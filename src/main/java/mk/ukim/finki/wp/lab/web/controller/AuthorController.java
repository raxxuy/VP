package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public String getAuthorsPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("authors", authorService.findAll());
        return "listAuthors";
    }

    @GetMapping("/author-form")
    public String getAddAuthorPage(Model model) {
        return "author-form";
    }

    @GetMapping("/author-form/{id}")
    public String getEditAuthorForm(@PathVariable Long id, Model model) {
        if (id == null) {
            return "redirect:/authors?error=BookNotFound";
        }

        Author author = authorService.findById(id);
        model.addAttribute("id", author.getId());
        model.addAttribute("name", author.getName());
        model.addAttribute("surname", author.getSurname());
        model.addAttribute("country", author.getCountry());
        model.addAttribute("biography", author.getBiography());
        return "author-form";
    }

    @PostMapping("/add")
    public String saveAuthor(@RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String country,
                           @RequestParam String biography) {
        authorService.save(name, surname, country, biography);
        return "redirect:/authors";
    }

    @PostMapping("/edit/{id}")
    public String editAuthor(@PathVariable Long id,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String country,
                           @RequestParam String biography) {
        authorService.edit(id, name, surname, country, biography);
        return "redirect:/authors";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        authorService.delete(id);
        return "redirect:/authors";
    }
}
