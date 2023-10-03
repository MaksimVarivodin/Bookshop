package petprojects.bookshop.dbModels.literatureinfrastructure.literatureinfo;

import jakarta.persistence.*;
import lombok.*;
import petprojects.bookshop.dbModels.literatureinfrastructure.author.AuthorModel;
import petprojects.bookshop.dbModels.literatureinfrastructure.genre.GenreModel;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Table
@Entity
@Data
public class LiteratureInfoModel {

    @Id

    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(nullable = false,
            unique = true,
            updatable = false)
    private Long id;

    @Column
    private Integer pages;

    @Column
    private Integer words;

    @Column(nullable = false,
            length = 100,
            unique = true)
    private String title;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(length = 10000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AuthorModel author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private GenreModel genre;

    public LiteratureInfoModel(Integer pages,
                               Integer words,
                               String title,
                               BigDecimal price,
                               String description,
                               AuthorModel author,
                               GenreModel genre) {
        this.pages = pages;
        this.words = words;
        this.title = title;
        this.price = price;
        this.description = description;
        this.author = author;
        this.genre = genre;
    }
}
