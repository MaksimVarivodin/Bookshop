package petprojects.bookshop.dbModels.LiteratureInfrastructure;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class LiteratureInfoModel {
    Long id;
    int pages;
    int words;
    String title;
    int price;
    String description;
    Long authorId;
    @ManyToOne
    @JoinColumn(referencedColumnName = "genreId")
    GenreModel genre;


}
