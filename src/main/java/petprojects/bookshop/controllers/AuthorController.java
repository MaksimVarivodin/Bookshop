package petprojects.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import petprojects.bookshop.services.AuthorService;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/view")
    public String viewAuthors(Model model) {
        model.addAttribute("authors", authorService.getAuthors());
        return "authors-page";
    }
    @GetMapping("/view-literature/{authorId}")
    public String viewAuthorsLiterature(
            @PathVariable("authorId") Long authorId,
            Model model) {
        model.addAttribute("literature", authorService.getAuthorsLiterature(authorId));
        model.addAttribute("author", authorService.getAuthorById(authorId));
        return "authors-literature-page";
    }
}
