package petprojects.bookshop.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petprojects.bookshop.models.literatureinfrastructure.AuthorModel;
import petprojects.bookshop.repositories.AuthorRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final String AUTHOR_EXISTS = "Author: Author with such a name {%s} already exists";
    private final String NO_SUCH_AUTHOR_EXISTS = "No author with such an id {%s} exists";

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /**
     * Retrieves a list of all authors.
     *
     * @return List of AuthorModel objects representing authors.
     */
    public List<AuthorModel> getAuthors() {
        // Retrieve all authors from the author repository.
        return authorRepository.findAll();
    }

    /**
     * Retrieves an author by their ID.
     *
     * @param authorId The ID of the author to retrieve.
     * @return An Optional containing the AuthorModel if found, otherwise an empty Optional.
     * @throws IllegalStateException If no author is found with the given ID.
     */
    public AuthorModel getAuthorById(Long authorId) {
        Optional<AuthorModel> author = authorRepository.findById(authorId);
        if (author.isEmpty()) {
            throw new IllegalStateException(String.format(NO_SUCH_AUTHOR_EXISTS, authorId));
        } else
            return author.get();
    }


    /**
     * Adds a new author to the author repository.
     *
     * @param authorModel The author model object containing the details of the author.
     * @throws IllegalStateException If an author with the same full name already exists in the repository.
     */
    public void addNewAuthor(AuthorModel authorModel) {
        // Check if an author with the same full name already exists in the repository
        authorRepository.findByFullName(authorModel.getFullName())
                .ifPresentOrElse(
                        // If author exists, throw an exception
                        author -> {
                            throw new IllegalStateException(String.format(AUTHOR_EXISTS, author.getFullName()));
                        },
                        // If author does not exist, save the new author to the repository
                        () -> authorRepository.save(authorModel)
                );
    }

    /**
     * Updates the fields of an author.
     *
     * @param authorId         The ID of the author.
     * @param fullName         The full name of the author.
     * @param birthDate        The birth date of the author.
     * @param deathDate        The death date of the author.
     * @param pictureLink      The picture link of the author.
     * @param biographyInShort The short biography of the author.
     * @throws IllegalStateException If the author does not exist.
     */
    public void updateAuthorFields(Long authorId,
                                   String fullName,
                                   LocalDate birthDate,
                                   LocalDate deathDate,
                                   String pictureLink,
                                   String biographyInShort) {
        authorRepository.findById(authorId).ifPresentOrElse(
                author -> {
                    if (fullName != null && !fullName.isEmpty())
                        author.setFullName(fullName);
                    if (birthDate != null && !birthDate.isBefore(LocalDate.now()) && (deathDate == null || deathDate.isAfter(birthDate)))
                        author.setBirthDate(birthDate);
                    if (deathDate != null && !deathDate.isBefore(LocalDate.now()))
                        author.setDeathDate(deathDate);
                    if (pictureLink != null && !pictureLink.isEmpty())
                        author.setPictureLink(pictureLink);
                    if (biographyInShort != null && !biographyInShort.isEmpty())
                        author.setBiographyInShort(biographyInShort);

                    authorRepository.save(author);
                }, () -> {
                    throw new IllegalStateException(String.format(NO_SUCH_AUTHOR_EXISTS, authorId));
                }
        );
    }

    /**
     * Update the author information.
     *
     * @param authorId     The ID of the author.
     * @param authorModel  The updated author model.
     */
    public void updateAuthor(Long authorId, AuthorModel authorModel) {
        updateAuthorFields(
                authorId,
                authorModel.getFullName(),
                authorModel.getBirthDate(),
                authorModel.getDeathDate(),
                authorModel.getPictureLink(),
                authorModel.getBiographyInShort()
        );
    }

    /**
     * Delete an author by their ID.
     *
     * @param authorId The ID of the author to delete.
     * @throws IllegalStateException If the author does not exist.
     */
    public void deleteAuthor(Long authorId) {
        authorRepository.findById(authorId)
                .ifPresentOrElse(
                        author -> {
                            authorRepository.deleteById(authorId);
                        },
                        () -> {
                            throw new IllegalStateException(String.format(NO_SUCH_AUTHOR_EXISTS, authorId));
                        }
                );
    }


}
