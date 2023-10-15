package petprojects.bookshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petprojects.bookshop.models.shopinfrastructure.PresentLiteratureModel;
import petprojects.bookshop.repositories.PresentLiteratureRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PresentLiteratureService {
    private final PresentLiteratureRepository presentLiteratureRepository;

    private final String LITERATURE_EXISTS = "Literature-Counter: Literature-Counter with such an id {%s} already exists";
    private final String NO_SUCH_LITERATURE_EXISTS = "Literature-Counter: No literature with such an id {%s} exists";
    private final ShopService shopService;
    private final LiteratureInfoService literatureInfoService;

    @Autowired
    public PresentLiteratureService(PresentLiteratureRepository presentLiteratureRepository,
                                    ShopService shopService,
                                    LiteratureInfoService literatureInfoService) {
        this.presentLiteratureRepository = presentLiteratureRepository;
        this.shopService = shopService;
        this.literatureInfoService = literatureInfoService;
    }

    /**
     * Retrieves a list of present literature models.
     *
     * @return a list of PresentLiteratureModel objects
     */
    public List<PresentLiteratureModel> getPresentLiterature() {
        // Retrieve all present literature models from the repository
        return presentLiteratureRepository.findAll();
    }
    /**
     * Retrieves a PresentLiteratureModel object by its ID.
     *
     * @param presentLiteratureId The ID of the PresentLiteratureModel to retrieve.
     * @return Optional containing the retrieved PresentLiteratureModel, or an empty Optional if not found.
     */
    public Optional<PresentLiteratureModel> getPresentLiteratureById(Long presentLiteratureId) {
        Optional<PresentLiteratureModel> presentLiterature = presentLiteratureRepository.findById(presentLiteratureId);
        if (presentLiterature.isEmpty())
            throw new IllegalStateException(String.format(NO_SUCH_LITERATURE_EXISTS, presentLiteratureId));
        return presentLiterature;
    }
    /**
     * Adds a new present literature to the repository.
     *
     * @param presentLiteratureModel The present literature model to add.
     * @throws IllegalStateException If the literature already exists in the repository.
     */
    public void addNewPresentLiterature(PresentLiteratureModel presentLiteratureModel) {
        presentLiteratureRepository.findByShopAndLiterature(presentLiteratureModel.getShop(), presentLiteratureModel.getLiterature())
                .ifPresentOrElse(
                        literature -> {
                            throw new IllegalStateException(String.format(LITERATURE_EXISTS, literature.getCounterId()));
                        },
                        () -> presentLiteratureRepository.save(presentLiteratureModel)
                );
    }
    /**
     * Updates the fields of a present literature.
     *
     * @param presentLiteratureId The ID of the present literature to update.
     * @param amount              The new amount value.
     * @param shop_id             The ID of the new shop.
     * @param literature_id       The ID of the new literature.
     * @throws IllegalStateException if the present literature with the given ID does not exist.
     */
    public void updatePresentLiteratureFields(Long presentLiteratureId,
                                              Integer amount,
                                              Long shop_id,
                                              Long literature_id) {
        presentLiteratureRepository.findById(presentLiteratureId)
                .ifPresentOrElse(
                        literature -> {
                            if (amount != null)
                                literature.setAmount(amount);
                            if (shop_id != null)
                                shopService.getShopById(shop_id).ifPresent(literature::setShop);
                            if (literature_id != null)
                                literatureInfoService.getLiteratureById(literature_id).ifPresent(literature::setLiterature);
                            presentLiteratureRepository.save(literature);
                        },
                        () -> {
                            throw new IllegalStateException(String.format(NO_SUCH_LITERATURE_EXISTS, presentLiteratureId));
                        }
                );
    }
    /**
     * Updates the present literature with the given ID using the provided present literature model.
     *
     * @param presentLiteratureId   The ID of the present literature to update.
     * @param presentLiteratureModel The present literature model containing the updated data.
     */
    public void updatePresentLiterature(Long presentLiteratureId, PresentLiteratureModel presentLiteratureModel) {
        updatePresentLiteratureFields(
                presentLiteratureId,
                presentLiteratureModel.getAmount(),
                presentLiteratureModel.getShop().getShopId(),
                presentLiteratureModel.getLiterature().getLiteratureId()
        );
    }
    /**
     * Deletes the present literature with the given ID.
     *
     * @param presentLiteratureId The ID of the present literature to delete.
     * @throws IllegalStateException If no present literature with the given ID exists.
     */
    public void deletePresentLiterature(Long presentLiteratureId) {
        presentLiteratureRepository.findById(presentLiteratureId)
                .ifPresentOrElse(
                        literature -> {
                            presentLiteratureRepository.deleteById(presentLiteratureId);
                        },
                        () -> {
                            throw new IllegalStateException(String.format(NO_SUCH_LITERATURE_EXISTS, presentLiteratureId));
                        }
                );
    }

}


