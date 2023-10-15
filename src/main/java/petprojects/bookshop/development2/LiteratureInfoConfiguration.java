package petprojects.bookshop.development;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import petprojects.bookshop.models.literatureinfrastructure.AuthorModel;
import petprojects.bookshop.models.literatureinfrastructure.GenreModel;
import petprojects.bookshop.models.literatureinfrastructure.LiteratureInfoModel;
import petprojects.bookshop.repositories.AuthorRepository;
import petprojects.bookshop.repositories.GenreRepository;
import petprojects.bookshop.services.AuthorService;
import petprojects.bookshop.services.GenreService;
import petprojects.bookshop.services.LiteratureInfoService;

import java.math.BigDecimal;

@Configuration
public class LiteratureInfoConfiguration {

    @Bean
    CommandLineRunner commandLineRunLitInfoConfig(LiteratureInfoService literatureInfoService, AuthorRepository authorRepository, GenreRepository genreRepository) {
        return args -> {
                AuthorModel authorModel1 = authorRepository.findById(1L).get();
                GenreModel genreModel1 = genreRepository.findById(1L).get();
//            Integer pages,
//            Integer words,
//            String title,
//            BigDecimal price,
//            String description,
//            AuthorModel author,
//            GenreModel genre
            LiteratureInfoModel literatureInfoModel = new LiteratureInfoModel(
                    5,
                    1000,
                    "Title",
                    BigDecimal.valueOf(100),
                    "Description",
                    authorModel1,
                    genreModel1

            );
            literatureInfoService.addNewLiterature(literatureInfoModel);
        };
    }
}
