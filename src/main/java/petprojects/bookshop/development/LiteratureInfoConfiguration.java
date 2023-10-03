package petprojects.bookshop.development;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import petprojects.bookshop.dbModels.literatureinfrastructure.author.AuthorService;
import petprojects.bookshop.dbModels.literatureinfrastructure.genre.GenreModel;
import petprojects.bookshop.dbModels.literatureinfrastructure.genre.GenreRepository;
import petprojects.bookshop.dbModels.literatureinfrastructure.genre.GenreService;
import petprojects.bookshop.dbModels.literatureinfrastructure.literatureinfo.LiteratureInfoModel;
import petprojects.bookshop.dbModels.literatureinfrastructure.literatureinfo.LiteratureInfoRepository;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class LiteratureInfoConfiguration {

    @Bean
    CommandLineRunner commandLineRunLitInfoConfig(LiteratureInfoRepository literatureInfoRepository, AuthorService authorService, GenreService genreService) {
        return args -> {

            LiteratureInfoModel literatureInfoModel = new LiteratureInfoModel(
                    1,
                    100,
                    "Title",
                    new BigDecimal(100),
                    "Description",
                    authorService.getAuthors(1L).get(0),
                    genreService.getGenres(1L).get(0)
            );
            literatureInfoRepository.save(literatureInfoModel);
        };
    }
}
