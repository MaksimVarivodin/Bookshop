package petprojects.bookshop.dbModels.LiteratureInfrastructure.Author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping
    public List<AuthorModel> getAuthors() {
        return authorService.getAuthors();
    }
}
