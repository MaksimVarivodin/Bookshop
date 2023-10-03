package petprojects.bookshop.dbModels.literatureinfrastructure.literatureinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LiteratureInfoRepository extends JpaRepository<LiteratureInfoModel, Long> {
    Optional<LiteratureInfoModel> findByTitle(String title);
}
