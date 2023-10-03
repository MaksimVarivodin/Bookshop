package petprojects.bookshop.dbModels.literatureinfrastructure.literatureinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petprojects.bookshop.dbModels.literatureinfrastructure.author.AuthorService;
import petprojects.bookshop.dbModels.literatureinfrastructure.genre.GenreService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class LiteratureInfoService {
    private final LiteratureInfoRepository literatureInfoRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final String LITERATURE_EXISTS = "Literature: Literature with such a title {%s} already exists";

    private final String NO_SUCH_LITERATURE_EXISTS = "No literature with such an id {%s} exists";
    @Autowired
    private LiteratureInfoService(
            LiteratureInfoRepository literatureInfoRepository,
            AuthorService authorService,
            GenreService genreService) {

        this.literatureInfoRepository = literatureInfoRepository;
        this.authorService = authorService;
        this.genreService = genreService;

    }

    public List<LiteratureInfoModel> getLiterature() {
        return literatureInfoRepository.findAll();
    }
    public Optional<LiteratureInfoModel> getLiteratureById(Long authorId) {
        return literatureInfoRepository.findById(authorId);
    }
    public void addNewLiterature(LiteratureInfoModel literatureInfoModel) {
        literatureInfoRepository.findByTitle(literatureInfoModel.getTitle())
                .ifPresentOrElse(
                        literature -> {
                            throw new IllegalStateException(String.format(LITERATURE_EXISTS, literature.getTitle()));
                        },
                        () -> literatureInfoRepository.save(literatureInfoModel)
                );
    }
    public void updateLiteratureFields(Long id,
                                       Integer pages,
                                       Integer words,
                                       String title,
                                       BigDecimal price,
                                       String description,
                                       Long authorId,
                                       Long genreId) {
        literatureInfoRepository.findById(id)
                .ifPresentOrElse(
                        literature -> {
                            if (pages != null && pages != 0)
                                literature.setPages(pages);
                            if (words != null && words != 0)
                                literature.setWords(words);
                            if (title != null && !title.isEmpty())
                                literature.setTitle(title);
                            if (price != null &&!price.equals(BigDecimal.valueOf(0.0)))
                                literature.setPrice(price);
                            if (description != null && !description.isEmpty())
                                literature.setDescription(description);

                            authorService.getAuthorById(authorId).ifPresent(literature::setAuthor);

                            genreService.getGenreById(genreId).ifPresent(literature::setGenre);


                            literatureInfoRepository.save(literature);

                        },
                        () -> {
                            throw new IllegalStateException(String.format(NO_SUCH_LITERATURE_EXISTS, id));
                        }
                );
    }
    public void updateLiterature(Long id, LiteratureInfoModel literatureInfoModel) {
        literatureInfoRepository.findById(id)
                .ifPresentOrElse(
                        literature -> {
                            if (literatureInfoModel.getPages() > 0)
                                literature.setPages(literatureInfoModel.getPages());
                            if (literatureInfoModel.getWords() > 0)
                                literature.setWords(literatureInfoModel.getWords());
                            if ( literatureInfoModel.getTitle() != null && !literatureInfoModel.getTitle().isEmpty())
                                literature.setTitle(literatureInfoModel.getTitle());
                            if (literatureInfoModel.getPrice() != null && !literatureInfoModel.getPrice().equals(BigDecimal.valueOf(0.0)))
                                literature.setPrice(literatureInfoModel.getPrice());
                            if (literatureInfoModel.getDescription() != null && !literatureInfoModel.getDescription().isEmpty())
                                literature.setDescription(literatureInfoModel.getDescription());

                            authorService.getAuthorById(literatureInfoModel.getAuthor().getId()).ifPresent(literature::setAuthor);

                            genreService.getGenreById(literatureInfoModel.getGenre().getId()).ifPresent(literature::setGenre);

                            literatureInfoRepository.save(literature);
                        },
                        () -> {
                            throw new IllegalStateException(String.format(NO_SUCH_LITERATURE_EXISTS, id));
                        }
                );
    }
    public void deleteLiterature(Long id) {
        literatureInfoRepository.findById(id)
                .ifPresentOrElse(
                        literatureInfoRepository::delete,
                        () -> {
                            throw new IllegalStateException(String.format(NO_SUCH_LITERATURE_EXISTS, id));
                        }
                );
    }
}
