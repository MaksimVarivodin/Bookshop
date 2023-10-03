package petprojects.bookshop.dbModels.literatureinfrastructure.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<GenreModel, Long> {
    @Query("select g from GenreModel g where g.genreName = ?1")
    Optional<GenreModel> findByGenreName(String genreName);
}
