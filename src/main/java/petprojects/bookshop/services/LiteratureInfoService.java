package petprojects.bookshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petprojects.bookshop.models.literatureinfrastructure.LiteratureInfoModel;
import petprojects.bookshop.repositories.LiteratureInfoRepository;

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

    /**
     * Retrieves a list of literature information.
     *
     * @return The list of literature information.
     */
    public List<LiteratureInfoModel> getLiterature() {
        return literatureInfoRepository.findAll();
    }

    /**
     * Retrieves a literature information model by author ID.
     *
     * @param authorId The ID of the author.
     * @return An optional containing the literature information model if found, otherwise empty.
     */
    public LiteratureInfoModel getLiteratureById(Long authorId) {
        Optional<LiteratureInfoModel> literatureInfoModel = literatureInfoRepository.findById(authorId);
        if (literatureInfoModel.isEmpty())
            throw new IllegalStateException(String.format(NO_SUCH_LITERATURE_EXISTS, authorId));
        else
            return literatureInfoModel.get();
    }

    /**
     * Adds a new literature to the repository.
     *
     * @param literatureInfoModel The literature info model to add.
     * @throws IllegalStateException If the literature with the same title already exists.
     */
    public void addNewLiterature(LiteratureInfoModel literatureInfoModel) {
        // Check if literature with the same title already exists
        literatureInfoRepository.findByTitle(literatureInfoModel.getTitle())
                .ifPresentOrElse(
                        literature -> {
                            // Throw exception if literature already exists
                            throw new IllegalStateException(String.format(LITERATURE_EXISTS, literature.getTitle()));
                        },
                        () -> literatureInfoRepository.save(literatureInfoModel)
                );
    }

    /**
     * Updates the fields of a literature based on the provided parameters.
     *
     * @param id          the ID of the literature to update
     * @param pages       the number of pages to set (null or 0 to not update)
     * @param words       the number of words to set (null or 0 to not update)
     * @param title       the title to set (null or empty to not update)
     * @param price       the price to set (null or zero to not update)
     * @param description the description to set (null or empty to not update)
     * @param authorId    the ID of the author to set (null to not update)
     * @param genreId     the ID of the genre to set (null to not update)
     */
    public void updateLiteratureFields(Long id, Integer pages, Integer words, String title, BigDecimal price,
                                       String description, Long authorId, Long genreId) {
        literatureInfoRepository.findById(id).ifPresentOrElse(literature -> {
            if (pages != null && pages != 0) {
                literature.setPages(pages);
            }
            if (words != null && words != 0) {
                literature.setWords(words);
            }
            if (title != null && !title.isEmpty()) {
                literature.setTitle(title);
            }
            if (price != null && price.compareTo(BigDecimal.ZERO) != 0) {
                literature.setPrice(price);
            }
            if (description != null && !description.isEmpty()) {
                literature.setDescription(description);
            }
            if (authorId != null) {
                literature.setAuthor(authorService.getAuthorById(authorId));
            }
            if (genreId != null) {
                literature.setGenre(genreService.getGenreById(genreId));
            }

            literatureInfoRepository.save(literature);
        }, () -> {
            throw new IllegalStateException(String.format(NO_SUCH_LITERATURE_EXISTS, id));
        });
    }

    /**
     * Updates the literature with the specified ID using the information provided in the literatureInfoModel.
     *
     * @param id The ID of the literature to update.
     * @param literatureInfoModel The new information for the literature.
     */
    public void updateLiterature(Long id, LiteratureInfoModel literatureInfoModel) {
        updateLiteratureFields(
                id,
                literatureInfoModel.getPages(),
                literatureInfoModel.getWords(),
                literatureInfoModel.getTitle(),
                literatureInfoModel.getPrice(),
                literatureInfoModel.getDescription(),
                literatureInfoModel.getAuthor().getAuthorId(),
                literatureInfoModel.getGenre().getGenreId()
        );
    }

    /**
     * Deletes literature with the given ID.
     *
     * @param id The ID of the literature to delete.
     * @throws IllegalStateException If no literature exists with the given ID.
     */
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
