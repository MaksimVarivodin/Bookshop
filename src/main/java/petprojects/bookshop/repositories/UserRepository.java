package petprojects.bookshop.repositories;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import petprojects.bookshop.dbModels.UserInfrastructure.UserModel;

import java.util.List;
import java.util.Optional;
@Repository
@Transactional(readOnly = true)
public interface UserRepository {
    /**
     * Finds a user model by email.
     *
     * @param  email    the email of the user to find
     * @return          an optional user model if found, empty optional otherwise
     */
    Optional<UserModel> findByEmail(String email);
    List<UserModel> getUsers();
}
