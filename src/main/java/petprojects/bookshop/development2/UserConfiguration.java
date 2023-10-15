package petprojects.bookshop.development;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import petprojects.bookshop.models.userinfrastructure.UserModel;
import petprojects.bookshop.models.userinfrastructure.UserRoles;
import petprojects.bookshop.services.UserService;

@Configuration
public class UserConfiguration {
    @Bean
    CommandLineRunner commandLineRunUserConfig(UserService userService) {

        return args -> {
//            String firstName,
//                     String lastName,
//                     String email,
//                     String password,
//                     Long phone,
//                     UserRoles role,
//                     Boolean locked,
//                     Boolean activated,
//                     String profilePictureLink
            UserModel   userModel = new UserModel(
                    "user",
                    "user",
                    "mail",
                    "password",
                    895845L,
                    UserRoles.USER,
                    false,
                    false,
                    "link"
            );

            userService.addNewUser(userModel);
        };
    }
}
