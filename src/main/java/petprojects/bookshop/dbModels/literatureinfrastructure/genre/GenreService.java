package petprojects.bookshop.dbModels.literatureinfrastructure.genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    private final String GENRE_EXISTS = "Genre: Genre with such a name {%s} already exists";
    private final String NO_SUCH_GENRE_EXISTS = "No genre with such an id {%s} exists";
    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
    public List<GenreModel> getGenres(Long genreId) {
        if (genreId != null){
            Optional<GenreModel> model = genreRepository.findById(genreId);
            if (model.isPresent())
                return Collections.singletonList(model.get());
            else {
                throw new IllegalStateException(String.format(NO_SUCH_GENRE_EXISTS, genreId));
            }
        }
        return genreRepository.findAll();
    }

    public void addNewGenre(GenreModel genreModel) {
        genreRepository.findByGenreName(genreModel.getGenreName())
                .ifPresentOrElse(
                        genre -> {
                            throw new IllegalStateException(String.format(GENRE_EXISTS, genre.getGenreName()));
                        },
                        () -> genreRepository.save(genreModel)
                );
    }

    public void updateGenreFields(Long genreId, String name) {
        genreRepository.findById(genreId)
                .ifPresentOrElse(
                        genre -> {
                            if (name != null && !name.isEmpty())
                                genre.setGenreName(name);
                        },
                        () -> {
                            throw new IllegalStateException(String.format(NO_SUCH_GENRE_EXISTS, genreId));
                        }
                );
    }
    public void updateGenre(Long genreId, GenreModel genreModel) {
        genreRepository.findById(genreId)
                .ifPresentOrElse(
                        genre -> {
                            if (genreModel.getGenreName() != null && !genreModel.getGenreName().isEmpty())
                                genre.setGenreName(genre.getGenreName());
                        },
                        () -> {
                            throw new IllegalStateException(String.format(NO_SUCH_GENRE_EXISTS, genreId));
                        }
                );
    }

    public void deleteGenre(Long genreId) {
        genreRepository.findById(genreId)
                .ifPresentOrElse(
                        genre -> {
                            genreRepository.deleteById(genreId);
                        },
                        () -> {
                            throw new IllegalStateException(String.format(NO_SUCH_GENRE_EXISTS, genreId));
                        }
                );
    }
}
