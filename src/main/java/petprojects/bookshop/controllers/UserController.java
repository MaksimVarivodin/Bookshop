package petprojects.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import petprojects.bookshop.dbModels.UserInfrastructure.UserModel;
import petprojects.bookshop.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    /**
     * Retrieves a list of user models.
     *
     * @return  a list of user models
     */
    @GetMapping
    public List<UserModel> getUsers() {
        return userService.getUsers();
    }
    @PostMapping
    public void registerNewUser(@RequestBody UserModel userModel){
        userService.addNewUser(userModel);
    }
}
