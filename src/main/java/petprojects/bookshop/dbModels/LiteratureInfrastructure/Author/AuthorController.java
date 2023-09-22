package petprojects.bookshop.dbModels.LiteratureInfrastructure.Author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
    private final AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * Retrieves a list of author models.
     *
     * @return The list of author models.
     */
    @GetMapping(path = "/get")
    public List<AuthorModel> getAuthors() {
        return authorService.getAuthors();
    }
    @PostMapping(path = "/add")
    public void addNewAuthor(@RequestBody AuthorModel authorModel) {
        authorService.addNewAuthor(authorModel);
    }

    @DeleteMapping(path = "/delete/{authorId}")
    public void deleteAuthor(@PathVariable("authorId") Long authorId) {
        authorService.deleteAuthor(authorId);
    }
    @PutMapping(path = "/update/{authorId}")
    public void updateAuthor(
            @PathVariable("authorId") Long authorId,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) LocalDate birthDate,
            @RequestParam(required = false) LocalDate deathDate,
            @RequestParam(required = false) String pictureLink,
            @RequestParam(required = false) String biographyInShort) {
         authorService.updateAuthor(
                 authorId,
                 fullName,
                 birthDate,
                 deathDate,
                 pictureLink,
                 biographyInShort
         );
    }

}
