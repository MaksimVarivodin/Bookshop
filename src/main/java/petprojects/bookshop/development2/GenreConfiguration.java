package petprojects.bookshop.development;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import petprojects.bookshop.models.literatureinfrastructure.GenreModel;
import petprojects.bookshop.repositories.GenreRepository;
import petprojects.bookshop.services.GenreService;


import java.util.List;

@Configuration
public class GenreConfiguration
{
    @Bean
    CommandLineRunner commandLineRunGenreConfig(GenreService genreService) {
        return args -> {

            GenreModel genreModel1 = new GenreModel(
                    "Genre1"
            );
            GenreModel genreModel2 = new GenreModel(
                    "Genre2"
            );
            genreService.addNewGenre(genreModel1);
            genreService.addNewGenre(genreModel2);
        };
    }
}
