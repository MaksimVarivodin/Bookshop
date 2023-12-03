package petprojects.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import petprojects.bookshop.models.literatureinfrastructure.AuthorModel;
import petprojects.bookshop.services.AuthorService;

import java.time.LocalDate;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public String viewAuthors(Model model) {
        model.addAttribute("authors", authorService.getAuthors());
        return "authors-page";
    }

    @GetMapping("/{authorId}")
    public String viewAuthorsLiterature(
            @PathVariable("authorId") Long authorId,
            Model model
    ) {
        model.addAttribute("literature", authorService.getAuthorsLiterature(authorId));
        model.addAttribute("author", authorService.getAuthorById(authorId));
        return "authors-literature-page";
    }

    @PostMapping()
    public String addNewAuthor(
            Model model,
            AuthorModel authorModel
    ) {
        authorService.addNewAuthor(authorModel);
        return viewAuthors(model);
    }

    @PutMapping("/{authorId}")
    public String updateAuthor(
            @PathVariable("authorId") Long authorId,
            Model model,
            AuthorModel authorModel
    ) {
        authorService.updateAuthor(authorId, authorModel);
        return viewAuthors(model);
    }

    @PatchMapping("/{authorId}")
    public String updateAuthorFields(@PathVariable("authorId") Long authorId,
                                     Model model,
                                     @RequestParam(required = false) String fullName,
                                     @RequestParam(required = false) LocalDate birthDate,
                                     @RequestParam(required = false) LocalDate deathDate,
                                     @RequestParam(required = false) String pictureLink,
                                     @RequestParam(required = false) String biographyInShort
    ) {
        authorService.updateAuthorFields(
                authorId,
                fullName,
                birthDate,
                deathDate,
                pictureLink,
                biographyInShort);
        return viewAuthors(model);
    }

    @DeleteMapping("/{authorId}")
    public String deleteAuthor(
            @PathVariable("authorId") Long authorId,
            Model model
    ) {
        authorService.deleteAuthor(authorId);
        return viewAuthors(model);
    }
}
