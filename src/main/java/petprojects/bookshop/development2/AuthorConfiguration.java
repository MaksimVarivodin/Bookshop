package petprojects.bookshop.development;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import petprojects.bookshop.models.literatureinfrastructure.AuthorModel;
import petprojects.bookshop.repositories.AuthorRepository;
import petprojects.bookshop.services.AuthorService;


import java.time.LocalDate;
import java.util.List;

@Configuration
public class AuthorConfiguration {


    @Bean
    CommandLineRunner commandLineRunAuthorConfig(AuthorService authorService) {
        return args -> {
            // Create the first author model
            AuthorModel authorModel1 = new AuthorModel(
                    "Author1",
                    LocalDate.of(2000, 1, 1),
                    LocalDate.of(2001, 1, 1),
                    "link1",
                    "short1"
            );

            // Create the second author model
            AuthorModel authorModel2 = new AuthorModel(
                    "Author2",
                    LocalDate.of(2001, 2, 2),
                    LocalDate.of(2002, 2, 2),
                    "link2",
                    "short2"
            );

            authorService.addNewAuthor(authorModel1);
            authorService.addNewAuthor(authorModel2);
        };
    }
}

