package petprojects.bookshop.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petprojects.bookshop.models.orderinfrastructure.TakenModel;
import petprojects.bookshop.services.TakenService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/taken")
public class TakenController {

    private final TakenService takenService;

    @Autowired
    public TakenController(TakenService takenService) {
        this.takenService = takenService;
    }

    /**
     * Get all taken orders
     *
     * @return List of TakenModel objects representing all the taken orders
     */
    @GetMapping
    public List<TakenModel> getTaken() {
        return takenService.getTaken();
    }

    @GetMapping("/{takenId}")
    public TakenModel getTakenById(@PathVariable("takenId") Long takenId) {
        return takenService.getTakenById(takenId);
    }
    /**
     * Add a new taken order
     *
     * @param takenModel The TakenModel object containing the details of the new taken order
     */
    @PostMapping
    public void addNewTaken(TakenModel takenModel) {
        takenService.addNewTaken(takenModel);
    }

    /**
     * Update an existing taken order
     *
     * @param takenId    The ID of the taken order to be updated
     * @param takenModel The TakenModel object containing the updated details of the taken order
     */
    @PutMapping(path = "/{takenId}")
    public void updateTaken(@PathVariable("takenId") Long takenId,
                            @Valid @RequestBody TakenModel takenModel) {
        takenService.updateTaken(takenId, takenModel);
    }

    /**
     * Update specific fields of an existing taken order
     *
     * @param takenId   The ID of the taken order to be updated
     * @param amount    The updated amount of the taken order
     * @param orderId   The updated ID of the order associated with the taken order
     * @param counterId The updated ID of the counter associated with the taken order
     */
    @PatchMapping(path = "/{takenId}")
    public void updateTakenFields(@PathVariable("takenId") Long takenId,
                                  @RequestParam Integer amount,
                                  @RequestParam Long orderId,
                                  @RequestParam Long counterId) {
        takenService.updateTakenFields(takenId,  amount, orderId, counterId);
    }

    /**
     * Delete a taken order
     *
     * @param takenId The ID of the taken order to be deleted
     */
    @DeleteMapping(path = "/{takenId}")
    public void deleteTaken(@PathVariable("takenId") Long takenId) {
        takenService.deleteTaken(takenId);
    }
}