package petprojects.bookshop.development;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import petprojects.bookshop.dbModels.literatureinfrastructure.author.AuthorService;
import petprojects.bookshop.dbModels.literatureinfrastructure.genre.GenreService;
import petprojects.bookshop.dbModels.literatureinfrastructure.literatureinfo.LiteratureInfoModel;
import petprojects.bookshop.dbModels.literatureinfrastructure.literatureinfo.LiteratureInfoRepository;
import petprojects.bookshop.dbModels.literatureinfrastructure.literatureinfo.LiteratureInfoService;

import java.math.BigDecimal;

@Configuration
public class LiteratureInfoConfiguration {

    @Bean
    CommandLineRunner commandLineRunLitInfoConfig(LiteratureInfoService literatureInfoService, AuthorService authorService, GenreService genreService) {
        return args -> {

            LiteratureInfoModel literatureInfoModel = new LiteratureInfoModel(
                    1,
                    100,
                    "Title",
                    new BigDecimal(100),
                    "Description",
                    authorService.getAuthorById(1L).get(),
                    genreService.getGenreById(52L).get()
            );
            literatureInfoService.addNewLiterature(literatureInfoModel);
        };
    }
}
