package petprojects.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import petprojects.bookshop.models.literatureinfrastructure.GenreModel;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<GenreModel, Long> {

    /**
     * Finds a genre by its name.
     *
     * @param genreName the name of the genre to find
     * @return an optional containing the genre, or an empty optional if the genre is not found
     */
    Optional<GenreModel> findByGenreName(String genreName);
}
