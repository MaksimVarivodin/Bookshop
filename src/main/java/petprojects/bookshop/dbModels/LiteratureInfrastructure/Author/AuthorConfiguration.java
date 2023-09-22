package petprojects.bookshop.dbModels.LiteratureInfrastructure.Author;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class AuthorConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(AuthorRepository authorRepository) {
        return args -> {
           AuthorModel  authorModel1 = new AuthorModel(
                    "Author1",
                    LocalDate.of(
                            2000,
                            1,
                            1),
                    "link1",
                    "short1"),
                        authorModel2 = new AuthorModel(
                    "Author2",
                    LocalDate.of(
                            2001,
                            2,
                            2),
                    "link2",
                    "short2");

            authorRepository.saveAll(
                    List.of(
                            authorModel1,
                            authorModel2)
            );
        } ;
    }
}

