package petprojects.bookshop.models.literatureinfrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Table (name = "literature")
@Entity
@Data
public class LiteratureInfoModel {

    @Id

    @GeneratedValue(
            strategy = GenerationType.TABLE
    )
    @Column(   name = "key_literature_id",
            nullable = false,
            unique = true,
            updatable = false)
    private Long literatureId;

    @Column(nullable = false)
    private Integer pages;

    @Column(nullable = false)
    private Integer words;

    @Column(nullable = false,
            length = 100,
            unique = true)
    private String title;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(length = 10000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id", insertable = false)
    private AuthorModel author;

    @ManyToOne
    @JoinColumn(name = "genre_id", insertable = false)
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
