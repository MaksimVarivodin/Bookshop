package petprojects.bookshop.dbModels.LiteratureInfrastructure.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {
    @Query("select a from AuthorModel a where a.fullName = ?1")
    Optional<AuthorModel> findByFullName(String fullName);
}
