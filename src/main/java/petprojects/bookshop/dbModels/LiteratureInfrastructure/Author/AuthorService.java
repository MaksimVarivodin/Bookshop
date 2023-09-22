package petprojects.bookshop.dbModels.LiteratureInfrastructure.Author;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorModel> getAuthors() {
        return authorRepository.findAll();
    }
}
