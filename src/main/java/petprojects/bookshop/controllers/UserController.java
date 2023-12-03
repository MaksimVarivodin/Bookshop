package petprojects.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import petprojects.bookshop.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping()
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users-page";
    }

    @GetMapping("/menu/{userId}")
    public String getUser(
            @PathVariable("userId") Long userId,
            Model model
    ){
        model.addAttribute("user", userService.getUserById(userId));
        return "user-menu-page";
    }
}
