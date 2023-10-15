package petprojects.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import petprojects.bookshop.models.literatureinfrastructure.LiteratureInfoModel;
import petprojects.bookshop.models.shopinfrastructure.PresentLiteratureModel;
import petprojects.bookshop.models.shopinfrastructure.ShopModel;

import java.util.Optional;

@Repository
public interface PresentLiteratureRepository  extends JpaRepository<PresentLiteratureModel, Long> {
    /**
     * Find a PresentLiteratureModel by the given ShopModel and LiteratureInfoModel.
     *
     * @param shop The ShopModel to search for.
     * @param literature The LiteratureInfoModel to search for.
     * @return An Optional containing the PresentLiteratureModel if found, otherwise an empty Optional.
     */
    Optional<PresentLiteratureModel> findByShopAndLiterature(ShopModel shop, LiteratureInfoModel literature);
}
