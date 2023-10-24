package petprojects.bookshop.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petprojects.bookshop.models.literatureinfrastructure.LiteratureInfoModel;
import petprojects.bookshop.services.LiteratureInfoService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/literature")
public class LiteratureInfoController {
    private final LiteratureInfoService literatureInfoService;

    @Autowired
    public LiteratureInfoController(LiteratureInfoService literatureInfoService) {
        this.literatureInfoService = literatureInfoService;
    }

    /**
     * Get all literature information.
     *
     * @return a list of literature info models
     */
    @GetMapping()
    public List<LiteratureInfoModel> getLiterature() {
        return literatureInfoService.getLiterature();
    }

    @GetMapping("/{literatureId}")
    public LiteratureInfoModel getLiteratureById(@PathVariable("literatureId") Long literatureId) {
        return literatureInfoService.getLiteratureById(literatureId);
    }
    /**
     * Add new literature information.
     *
     * @param literatureInfoModel the literature info model to be added
     */
    @PostMapping()
    public void addNewLiterature(@RequestBody LiteratureInfoModel literatureInfoModel) {
        literatureInfoService.addNewLiterature(literatureInfoModel);
    }

    /**
     * Update an existing literature.
     *
     * @param literatureId         the ID of the literature to be updated
     * @param literatureInfoModel  the updated literature info model
     */
    @PutMapping(path = "/{literatureId}")
    public void updateLiterature(
            @PathVariable("literatureId") Long literatureId,
            @Valid @RequestBody LiteratureInfoModel literatureInfoModel
    ) {
        literatureInfoService.updateLiterature(literatureId, literatureInfoModel);
    }

    /**
     * Update specific fields of an existing literature.
     *
     * @param literatureId     the ID of the literature to be updated
     * @param pages            the updated number of pages (optional)
     * @param words            the updated number of words (optional)
     * @param title            the updated title (optional)
     * @param price            the updated price (optional)
     * @param description      the updated description (optional)
     * @param authorId         the updated author ID (optional)
     * @param genreId          the updated genre ID (optional)
     */
    @PatchMapping(path = "/{literatureId}")
    public void updateLiterature(
            @PathVariable("literatureId") Long literatureId,
            @RequestParam(required = false) Integer pages,
            @RequestParam(required = false) Integer words,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Long genreId
    ) {
        literatureInfoService.updateLiteratureFields(literatureId,
                pages,
                words,
                title,
                price,
                description,
                authorId,
                genreId);
    }

    /**
     * Delete an existing literature.
     *
     * @param literatureId  the ID of the literature to be deleted
     */
    @DeleteMapping(path = "/{literatureId}")
    public void deleteLiterature(
            @PathVariable("literatureId") Long literatureId
    ) {
        literatureInfoService.deleteLiterature(literatureId);
    }

}