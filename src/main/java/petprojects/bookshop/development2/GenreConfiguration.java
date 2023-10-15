package petprojects.bookshop.development2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import petprojects.bookshop.dbModels.literatureinfrastructure.genre.GenreModel;
import petprojects.bookshop.dbModels.literatureinfrastructure.genre.GenreRepository;

import java.util.List;

@Configuration
public class GenreConfiguration
{
    @Bean
    CommandLineRunner commandLineRunGenreConfig(GenreRepository genreRepository) {
        return args -> {

            GenreModel genreModel1 = new GenreModel(
                    "Genre1"
            );
            GenreModel genreModel2 = new GenreModel(
                    "Genre2"
            );

            genreRepository.saveAll(List.of(genreModel1, genreModel2));
        };
    }
}
