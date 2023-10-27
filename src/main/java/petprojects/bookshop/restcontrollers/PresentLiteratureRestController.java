package petprojects.bookshop.restcontrollers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petprojects.bookshop.models.shopinfrastructure.PresentLiteratureModel;
import petprojects.bookshop.services.PresentLiteratureService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/present-literature")
public class PresentLiteratureRestController {

    private final PresentLiteratureService presentLiteratureService;

    @Autowired
    public PresentLiteratureRestController(PresentLiteratureService presentLiteratureService) {
        this.presentLiteratureService = presentLiteratureService;
    }

    /**
     * Get all present literature.
     *
     * @return a list of present literature models
     */
    @GetMapping()
    public List<PresentLiteratureModel> getPresentLiterature() {
        return presentLiteratureService.getPresentLiterature();
    }

    @GetMapping("/{presentLiteratureId}")
    public PresentLiteratureModel getPresentLiteratureById(@PathVariable("presentLiteratureId") Long presentLiteratureId) {
        return presentLiteratureService.getPresentLiteratureById(presentLiteratureId);
    }
    /**
     * Add new present literature.
     *
     * @param presentLiteratureModel the present literature model to be added
     */
    @PostMapping
    public void addNewPresentLiterature(@RequestBody PresentLiteratureModel presentLiteratureModel) {
        presentLiteratureService.addNewPresentLiterature(presentLiteratureModel);
    }

    /**
     * Update an existing present literature.
     *
     * @param presentLiteratureId    the ID of the present literature to be updated
     * @param presentLiteratureModel the updated present literature model
     */
    @PutMapping(path = "/{presentLiteratureId}")
    public void updatePresentLiterature(
            @PathVariable("presentLiteratureId") Long presentLiteratureId,
            @Valid @RequestBody PresentLiteratureModel presentLiteratureModel
    ) {
        presentLiteratureService.updatePresentLiterature(presentLiteratureId, presentLiteratureModel);
    }

    /**
     * Update specific fields of an existing present literature.
     *
     * @param presentLiteratureId the ID of the present literature to be updated
     * @param amount              the updated amount (optional)
     * @param shop_id             the updated shop ID (optional)
     * @param literature_id       the updated literature ID (optional)
     */
    @PatchMapping(path = "/{presentLiteratureId}")
    public void updatePresentLiteratureFields(
            @PathVariable("presentLiteratureId") Long presentLiteratureId,
            @RequestParam(required = false) Integer amount,
            @RequestParam(required = false) Long shop_id,
            @RequestParam(required = false) Long literature_id
    ) {
        presentLiteratureService.updatePresentLiteratureFields(presentLiteratureId, amount, shop_id, literature_id);
    }

    /**
     * Delete an existing present literature.
     *
     * @param presentLiteratureId the ID of the present literature to be deleted
     */
    @DeleteMapping(path = "/{presentLiteratureId}")
    public void deletePresentLiterature(@PathVariable("presentLiteratureId") Long presentLiteratureId) {
        presentLiteratureService.deletePresentLiterature(presentLiteratureId);
    }

}