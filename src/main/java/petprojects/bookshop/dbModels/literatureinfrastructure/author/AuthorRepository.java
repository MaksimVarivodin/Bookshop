package petprojects.bookshop.dbModels.literatureinfrastructure.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {

    /**
     * Find an author by their full name
     */
    Optional<AuthorModel> findByFullName(String fullName);
}
