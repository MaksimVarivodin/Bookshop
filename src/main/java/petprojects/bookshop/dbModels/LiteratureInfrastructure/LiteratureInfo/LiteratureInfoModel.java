package petprojects.bookshop.dbModels.LiteratureInfrastructure.LiteratureInfo;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import petprojects.bookshop.dbModels.LiteratureInfrastructure.Genre.GenreModel;

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