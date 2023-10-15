package petprojects.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import petprojects.bookshop.models.literatureinfrastructure.AuthorModel;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {

    /**
     * Find an author by their full name
     */
    Optional<AuthorModel> findByFullName(String fullName);
}
