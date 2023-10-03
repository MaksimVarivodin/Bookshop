package petprojects.bookshop.dbModels.literatureinfrastructure.author;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<AuthorModel> getAuthorById(Long authorId) {
        return authorRepository.findById(authorId);
    }


    /**
     * Adds a new author to the author repository.
     *
     * @param authorModel The author model object containing the details of the author.
     * @throws IllegalStateException If an author with the same full name already exists in the repository.
     */
    public void addNewAuthor(AuthorModel authorModel) {
        authorRepository.findByFullName(authorModel.getFullName())
                .ifPresentOrElse(
                        author -> {
                            throw new IllegalStateException(String.format(AUTHOR_EXISTS, author.getFullName()));
                        },
                        () -> authorRepository.save(authorModel)
                );
    }

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
                    if (birthDate != null && !birthDate.isBefore(LocalDate.now()) && (deathDate == null || deathDate.isAfter(birthDate)) )
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

    public void updateAuthor(Long authorId, AuthorModel authorModel) {
        authorRepository.findById(authorId).ifPresentOrElse(
                author -> {
                    if (authorModel.getFullName() != null && !authorModel.getFullName().isEmpty())
                        author.setFullName(authorModel.getFullName());
                    if  (authorModel.getBirthDate() != null && !authorModel.getBirthDate().isBefore(LocalDate.now())
                            && (authorModel.getDeathDate() == null || authorModel.getDeathDate().isAfter(authorModel.getBirthDate())))
                        author.setBirthDate(authorModel.getBirthDate());
                    if (authorModel.getDeathDate() != null && !authorModel.getDeathDate().isBefore(LocalDate.now()))
                        author.setDeathDate(authorModel.getDeathDate());
                    if (authorModel.getPictureLink() != null && !authorModel.getPictureLink().isEmpty())
                        author.setPictureLink(authorModel.getPictureLink());
                    if (authorModel.getBiographyInShort() != null && !authorModel.getBiographyInShort().isEmpty())
                        author.setBiographyInShort(authorModel.getBiographyInShort());

                    authorRepository.save(author);
                }, () -> {
                    throw new IllegalStateException(String.format(NO_SUCH_AUTHOR_EXISTS, authorId));
                }
        );
    }
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
