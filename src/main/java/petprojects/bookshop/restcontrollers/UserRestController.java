package petprojects.bookshop.restcontrollers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petprojects.bookshop.models.orderinfrastructure.OrderModel;
import petprojects.bookshop.models.userinfrastructure.UserModel;
import petprojects.bookshop.models.userinfrastructure.UserRoles;
import petprojects.bookshop.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all users
     *
     * @return List of UserModel objects representing all the users
     */
    @GetMapping
    public List<UserModel> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public UserModel getUserById(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }
    @GetMapping("/orders/{userId}")
    public List<OrderModel> getUserOrdersById(@PathVariable("userId") Long userId) {
        return userService.getUserOrdersById(userId);
    }
    /**
     * Add a new user
     *
     * @param userModel The UserModel object containing the details of the new user
     */
    @PostMapping
    public void addNewUser(UserModel userModel) {
        userService.addNewUser(userModel);
    }

    /**
     * Update an existing user
     *
     * @param userId     The ID of the user to be updated
     * @param userModel The UserModel object containing the updated details of the user
     */
    @PutMapping(path = "/{userId}")
    public void updateUser(@PathVariable("userId") Long userId,
                           @Valid @RequestBody UserModel userModel) {
        userService.updateUser(userId, userModel);
    }

    /**
     * Update specific fields of an existing user
     *
     * @param userId       The ID of the user to be updated
     * @param name         The updated name of the user (optional)
     * @param lastName     The updated last name of the user (optional)
     * @param email        The updated email of the user (optional)
     * @param password     The updated password of the user (optional)
     * @param phone        The updated phone number of the user (optional)
     * @param role         The updated role of the user (optional)
     * @param locked       The updated locked status of the user (optional)
     * @param activated    The updated activated status of the user (optional)
     * @param pictureLink  The updated picture link of the user (optional)
     */
    @PatchMapping(path = "/{userId}")
    public void updateUserFields(@PathVariable("userId") Long userId,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) String lastName,
                                 @RequestParam(required = false) String email,
                                 @RequestParam(required = false) String password,
                                 @RequestParam(required = false) Long phone,
                                 @RequestParam(required = false) UserRoles role,
                                 @RequestParam(required = false) Boolean locked,
                                 @RequestParam(required = false) Boolean activated,
                                 @RequestParam(required = false) String pictureLink) {
        userService.updateUserFields(userId, name, lastName, email, password, phone, role, locked, activated, pictureLink);
    }

    /**
     * Delete a user
     *
     * @param userId The ID of the user to be deleted
     */
    @DeleteMapping(path = "/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }
}