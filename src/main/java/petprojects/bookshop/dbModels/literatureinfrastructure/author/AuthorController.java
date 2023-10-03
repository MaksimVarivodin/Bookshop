package petprojects.bookshop.dbModels.literatureinfrastructure.author;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
     * Returns a list of AuthorModel objects.
     *
     * @return a list of AuthorModel objects
     */
    @GetMapping(path = "/{authorId}")
    public List<AuthorModel> getAuthors(@PathVariable ("authorId") Long authorId) {
        return authorService.getAuthors(authorId);
    }

    /**
     * Adds a new author.
     *
     * @param authorModel The AuthorModel object containing the details of the author.
     */
    @PostMapping()
    public void addNewAuthor(@RequestBody AuthorModel authorModel) {
        // Call the addNewAuthor method of the authorService to add the new author.
        authorService.addNewAuthor(authorModel);
    }

    /**
     * Delete an author by their ID.
     *
     * @param authorId The ID of the author to delete.
     */

    @PutMapping(path = "/{authorId}")
    public void updateAuthor(@PathVariable("authorId") Long authorId,
                             @Valid @RequestBody AuthorModel authorModel) {
        // Call the updateAuthor: Firstname Lastname of the authorService
        authorService.updateAuthor(authorId, authorModel);

    }

    /**
     * Updates the author with the given authorId.
     *
     * @param authorId          the ID of the author
     * @param fullName          the full name of the author (optional)
     * @param birthDate         the birthdate of the author (optional)
     * @param deathDate         the death-date of the author (optional)
     * @param pictureLink       the picture link of the author (optional)
     * @param biographyInShort  the biography in short of the author (optional)
     */
    @PatchMapping(path = "/{authorId}")
    public void updateAuthorFields (
            @PathVariable("authorId") Long authorId,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) LocalDate birthDate,
            @RequestParam(required = false) LocalDate deathDate,
            @RequestParam(required = false) String pictureLink,
            @RequestParam(required = false) String biographyInShort) {

        // Call the author service to update the author
        authorService.updateAuthorFields(
                authorId,
                fullName,
                birthDate,
                deathDate,
                pictureLink,
                biographyInShort);
    }

    @DeleteMapping(path = "/{authorId}")
    public void deleteAuthor(@PathVariable("authorId") Long authorId) {
        // Call the deleteAuthor method of the authorService
        authorService.deleteAuthor(authorId);
    }
}