package petprojects.bookshop.dbModels.literatureinfrastructure.genre;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }
    @GetMapping()
    public List<GenreModel> getGenres() {
        return genreService.getGenres();
    }

    @PostMapping()
    public void addNewGenre(GenreModel genreModel) {
        genreService.addNewGenre(genreModel);
    }
    @PutMapping(path = "/{genreId}")
    public void updateGenre(@PathVariable("genreId")
                            Long genreId,
                            @Valid
                            @RequestBody
                            GenreModel genreModel) {
        genreService.updateGenre(genreId, genreModel);
    }
    @PatchMapping(path = "/{genreId}")
    public void updateGenreFields(@PathVariable("genreId")
                                Long genreId,
                            @RequestParam
                                String name) {
        genreService.updateGenreFields(genreId, name);
    }

    @DeleteMapping(path = "/{genreId}")
    public void deleteGenre(@PathVariable("genreId") Long genreId) {
        genreService.deleteGenre(genreId);
    }
}
