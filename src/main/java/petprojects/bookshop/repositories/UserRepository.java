package petprojects.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import petprojects.bookshop.models.userinfrastructure.UserModel;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    /**
     * Finds a user model by email.
     *
     * @param email The email of the user to find.
     * @return an Optional containing the UserModel if found, or an empty Optional if not found.
     */
    Optional<UserModel> findByEmail(String email);
}
