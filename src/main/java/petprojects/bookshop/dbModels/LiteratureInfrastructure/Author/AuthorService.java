package petprojects.bookshop.dbModels.LiteratureInfrastructure.Author;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final String AUTHOR_EXISTS = "Author: Author with such a name already exists";
    private final String NO_AUTHOR_EXISTS = "No author with such an id {%s} exists";
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorModel> getAuthors() {
        return authorRepository.findAll();
    }

    public void addNewAuthor(AuthorModel authorModel) {
        authorRepository.findByFullName(authorModel.getFullName()).ifPresentOrElse(
                author -> {
                    throw new IllegalStateException(AUTHOR_EXISTS);
                }, () -> authorRepository.save(authorModel)
        );

    }

    public void deleteAuthor(Long authorId) {
        authorRepository.findById(authorId).ifPresentOrElse(
                author -> {
                    authorRepository.deleteById(authorId);
                }, () -> {
                    throw new IllegalStateException(String.format(NO_AUTHOR_EXISTS, authorId));
                }
        );
    }
    @Transactional
    public void updateAuthor(Long authorId,
                             String fullName,
                             LocalDate birthDate,
                             LocalDate deathDate,
                             String pictureLink,
                             String biographyInShort) {
        authorRepository.findById(authorId).ifPresentOrElse(
                author -> {

                    author.setFullName(fullName!=null?fullName:author.getFullName());
                    author.setBirthDate(birthDate!=null?birthDate:author.getBirthDate());
                    author.setDeathDate(deathDate!=null?deathDate:author.getDeathDate());
                    author.setPictureLink(pictureLink    !=null?pictureLink:author.getPictureLink());
                    author.setBiographyInShort(biographyInShort !=null?biographyInShort:author.getBiographyInShort());
//                    authorRepository.save(author);
                }, () -> {
                    throw new IllegalStateException(String.format(NO_AUTHOR_EXISTS, authorId));
                }
        );
    }
}
