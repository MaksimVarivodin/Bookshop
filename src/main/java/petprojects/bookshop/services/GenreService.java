package petprojects.bookshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petprojects.bookshop.models.literatureinfrastructure.GenreModel;
import petprojects.bookshop.repositories.GenreRepository;

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

    /**
     * Retrieves all genre models from the database.
     *
     * @return a list of genre models
     */
    public List<GenreModel> getGenres() {
        return genreRepository.findAll();
    }

    /**
     * Retrieves a genre by its ID.
     *
     * @param genreId the ID of the genre to retrieve
     * @return an Optional containing the genre if found
     * @throws IllegalStateException if no genre with the given ID exists
     */
    public GenreModel getGenreById(Long genreId) {
        Optional<GenreModel> genre = genreRepository.findById(genreId);
        if (genre.isEmpty()) {
            throw new IllegalStateException(String.format(NO_SUCH_GENRE_EXISTS, genreId));
        }   else
            return genre.get();

    }

    /**
     * Adds a new genre to the repository.
     *
     * @param genreModel The genre to add.
     * @throws IllegalStateException If the genre already exists in the repository.
     */
    public void addNewGenre(GenreModel genreModel) {
        genreRepository.findByGenreName(genreModel.getGenreName())
                .ifPresentOrElse(
                        genre -> {
                            throw new IllegalStateException(String.format(GENRE_EXISTS, genre.getGenreName()));
                        },
                        () -> genreRepository.save(genreModel)
                );
    }

    /**
     * Updates the specified genre's fields.
     *
     * @param genreId the ID of the genre to update
     * @param name the new name for the genre
     * @throws IllegalStateException if the genre does not exist
     */
    public void updateGenreFields(Long genreId, String name) {
        genreRepository.findById(genreId)
                .ifPresentOrElse(
                        genre -> {
                            if (name != null && !name.isEmpty()) {
                                genre.setGenreName(name);
                            }
                        },
                        () -> {
                            throw new IllegalStateException(String.format(NO_SUCH_GENRE_EXISTS, genreId));
                        }
                );
    }

    /**
     * Updates the genre with the given genre ID.
     *
     * @param genreId The ID of the genre to be updated.
     * @param genreModel The GenreModel object containing the updated genre information.
     */
    public void updateGenre(Long genreId, GenreModel genreModel) {
        // Update the genre fields
        updateGenreFields(
                genreId,
                genreModel.getGenreName()
        );
    }

    /**
     * Deletes a genre by genreId.
     *
     * @param genreId the id of the genre to be deleted
     * @throws IllegalStateException if the genre does not exist
     */
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
