package petprojects.bookshop.restcontrollers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petprojects.bookshop.models.literatureinfrastructure.AuthorModel;
import petprojects.bookshop.models.literatureinfrastructure.LiteratureInfoModel;
import petprojects.bookshop.services.AuthorService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorRestController {
    private final AuthorService authorService;

    @Autowired
    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * Get all authors.
     *
     * @return a list of author models
     */
    @GetMapping()
    public List<AuthorModel> getAuthors() {
        return authorService.getAuthors();
    }

    @GetMapping("/{authorId}")
    public AuthorModel getAuthorById(@PathVariable("authorId") Long authorId) {
        return authorService.getAuthorById(authorId);
    }
    @GetMapping("/literature/{authorId}")
    public List<LiteratureInfoModel> getAuthorsLiterature(@PathVariable("authorId") Long authorId) {
        return authorService.getAuthorsLiterature(authorId);
    }
    /**
     * Add a new author.
     *
     * @param authorModel the author model to be added
     */
    @PostMapping()
    public void addNewAuthor(@RequestBody AuthorModel authorModel) {
        authorService.addNewAuthor(authorModel);
    }

    /**
     * Update an existing author.
     *
     * @param authorId     the ID of the author to be updated
     * @param authorModel  the updated author model
     */
    @PutMapping(path = "/{authorId}")
    public void updateAuthor(@PathVariable("authorId") Long authorId,
                             @Valid @RequestBody AuthorModel authorModel) {
        authorService.updateAuthor(authorId, authorModel);
    }

    /**
     * Update specific fields of an existing author.
     *
     * @param authorId         the ID of the author to be updated
     * @param fullName         the updated full name (optional)
     * @param birthDate        the updated birthdate (optional)
     * @param deathDate        the updated death date (optional)
     * @param pictureLink      the updated picture link (optional)
     * @param biographyInShort the updated biography in short (optional)
     */
    @PatchMapping(path = "/{authorId}")
    public void updateAuthorFields(
            @PathVariable("authorId") Long authorId,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) LocalDate birthDate,
            @RequestParam(required = false) LocalDate deathDate,
            @RequestParam(required = false) String pictureLink,
            @RequestParam(required = false) String biographyInShort) {
        authorService.updateAuthorFields(
                authorId,
                fullName,
                birthDate,
                deathDate,
                pictureLink,
                biographyInShort);
    }

    /**
     * Delete an existing author.
     *
     * @param authorId the ID of the author to be deleted
     */
    @DeleteMapping(path = "/{authorId}")
    public void deleteAuthor(@PathVariable("authorId") Long authorId) {
        authorService.deleteAuthor(authorId);
    }
}