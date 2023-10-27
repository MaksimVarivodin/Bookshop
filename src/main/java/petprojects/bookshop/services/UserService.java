package petprojects.bookshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import petprojects.bookshop.models.orderinfrastructure.OrderModel;
import petprojects.bookshop.models.userinfrastructure.UserModel;
import petprojects.bookshop.models.userinfrastructure.UserRoles;
import petprojects.bookshop.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final String USER_NOT_FOUND_MSG = "User with id {%s} not found";

    private final String USER_EXISTS_MSG = "User with id {%s} already exists";

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> getUsers() {
        return userRepository.findAll();
    }
    public UserModel getUserById(Long userId) {
        Optional<UserModel> user = userRepository.findById(userId);
        if (user.isEmpty())
            throw new IllegalStateException(String.format(USER_NOT_FOUND_MSG, userId));
        else
            return user.get();
    }
    public List<OrderModel>
    getUserOrdersById(Long userId) {
        UserModel user = getUserById(userId);
        if (user == null) {
            throw new NullPointerException("User not found");
        }
        return user.getOrders();
    }
    @Transactional
    public void addNewUser(UserModel userModel) {
        userRepository.findByEmail(userModel.getEmail())
                .ifPresentOrElse(
                        user -> {
                            throw new IllegalStateException(String.format(USER_EXISTS_MSG, userModel.getEmail()));
                        },
                        () -> userRepository.save(userModel)
                );
    }
    @Transactional
    public void updateUserFields(Long userId,
                                 String email,
                                 String password,
                                 String firstName,
                                 String lastName,
                                 Long phone,
                                 UserRoles role,
                                 Boolean locked,
                                 Boolean activated,
                                 String pictureLink
                                 ) {
        userRepository.findById(userId)
                .ifPresentOrElse(
                        user -> {
                            if (email != null && !email.isEmpty())
                                user.setEmail(email);
                            if (password != null && !password.isEmpty())
                                user.setPassword(password);
                            if (firstName != null && !firstName.isEmpty())
                                user.setFirstName(firstName);
                            if (lastName != null && !lastName.isEmpty())
                                user.setLastName(lastName);
                            if (phone != null)
                                user.setPhone(phone);
                            if (role != null)
                                user.setRole(role);
                            if (locked != null)
                                user.setLocked(locked);
                            if (activated != null)
                                user.setActivated(activated);
                            if (pictureLink != null && !pictureLink.isEmpty())
                                user.setProfilePictureLink(pictureLink);
                            userRepository.save(user);
                        },
                        () -> {
                            throw new IllegalStateException(String.format(USER_NOT_FOUND_MSG, userId));
                        });
    }
    @Transactional
    public void updateUser(Long userId, UserModel userModel) {
     updateUserFields(userId,
             userModel.getEmail(),
             userModel.getPassword(),
             userModel.getFirstName(),
             userModel.getLastName(),
             userModel.getPhone(),
             userModel.getRole(),
             userModel.getLocked(),
             userModel.getActivated(),
             userModel.getProfilePictureLink())
             ;
    }
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .ifPresentOrElse(
                        userRepository::delete,
                        () -> {
                            throw new IllegalStateException(String.format(USER_NOT_FOUND_MSG, userId));
                        });
    }

}
