package petprojects.bookshop.dbModels.userinfrastructure;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final static String USER_LIST_EMPTY_MSG = "User list is empty";

    /**
     * This function loads a user by their username.
     *
     * @param  email  the email of the user
     * @return        the UserDetails object representing the user
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).
                orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }
    /**
     * Retrieves the list of user models.
     *
     * @return  the list of user models
     */
    public List<UserModel> getUsers(){
        List<UserModel> temp = userRepository.findAll();
       if (temp.isEmpty())
        throw new NullPointerException(USER_LIST_EMPTY_MSG);
       return temp;
    }

    public void addNewUser(UserModel userModel) {
    }
}

