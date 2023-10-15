package petprojects.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import petprojects.bookshop.models.literatureinfrastructure.LiteratureInfoModel;

import java.util.Optional;

@Repository
public interface LiteratureInfoRepository extends JpaRepository<LiteratureInfoModel, Long> {
    /**
     * Retrieves a LiteratureInfoModel object by its title.
     *
     * @param title The title of the literature to search for.
     * @return An Optional containing the LiteratureInfoModel object if found, or an empty Optional if not found.
     */
    Optional<LiteratureInfoModel> findByTitle(String title);
}
