package petprojects.bookshop.restcontrollers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petprojects.bookshop.models.literatureinfrastructure.GenreModel;
import petprojects.bookshop.services.GenreService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreRestController {
    private final GenreService genreService;

    @Autowired
    public GenreRestController(GenreService genreService) {
        this.genreService = genreService;
    }

    /**
     * Get all genres.
     *
     * @return a list of genre models
     */
    @GetMapping()
    public List<GenreModel> getGenres() {
        return genreService.getGenres();
    }

    @GetMapping("/{genreId}")
    public GenreModel getGenreById(@PathVariable("genreId") Long genreId) {
        return genreService.getGenreById(genreId);
    }
    /**
     * Add a new genre.
     *
     * @param genreModel the genre model to be added
     */
    @PostMapping()
    public void addNewGenre(@RequestBody GenreModel genreModel) {
        genreService.addNewGenre(genreModel);
    }

    /**
     * Update an existing genre.
     *
     * @param genreId     the ID of the genre to be updated
     * @param genreModel  the updated genre model
     */
    @PutMapping(path = "/{genreId}")
    public void updateGenre(@PathVariable("genreId") Long genreId,
                            @Valid @RequestBody GenreModel genreModel) {
        genreService.updateGenre(genreId, genreModel);
    }

    /**
     * Update specific fields of an existing genre.
     *
     * @param genreId  the ID of the genre to be updated
     * @param name     the updated genre name
     */
    @PatchMapping(path = "/{genreId}")
    public void updateGenreFields(@PathVariable("genreId") Long genreId,
                                  @RequestParam String name) {
        genreService.updateGenreFields(genreId, name);
    }

    /**
     * Delete an existing genre.
     *
     * @param genreId the ID of the genre to be deleted
     */
    @DeleteMapping(path = "/{genreId}")
    public void deleteGenre(@PathVariable("genreId") Long genreId) {
        genreService.deleteGenre(genreId);
    }
}