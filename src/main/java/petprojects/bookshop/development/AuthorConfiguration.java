package petprojects.bookshop.development;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import petprojects.bookshop.dbModels.literatureinfrastructure.author.AuthorModel;
import petprojects.bookshop.dbModels.literatureinfrastructure.author.AuthorRepository;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class AuthorConfiguration {

    /**
     * Creates a CommandLineRunner that saves two author models to the author repository.
     *
     * @param authorRepository the repository to save the author models to
     * @return a CommandLineRunner that saves the author models
     */
    @Bean
    CommandLineRunner commandLineRunAuthorConfig(AuthorRepository authorRepository) {
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

            // Save the author models to the repository
            authorRepository.saveAll(List.of(authorModel1, authorModel2));
        };
    }
}

