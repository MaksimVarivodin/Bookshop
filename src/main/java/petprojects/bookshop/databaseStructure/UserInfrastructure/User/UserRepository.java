package petprojects.bookshop.databaseStructure.UserInfrastructure.User;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
@Transactional(readOnly = true)
public interface UserRepository {
    Optional<UserModel> findByEmail(String email);
}
