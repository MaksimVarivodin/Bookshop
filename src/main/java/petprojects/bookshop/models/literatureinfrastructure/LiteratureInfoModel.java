package petprojects.bookshop.models.literatureinfrastructure;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
            strategy = GenerationType.IDENTITY
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

    @Column(columnDefinition = "TEXT" , length = 5000)
    @Size(max = 10000)
    private String description;

    @Column(columnDefinition = "TEXT" , length = 2083)
    @Size(max = 10000)
    private String bookCoverLink;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private AuthorModel author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    private GenreModel genre;

    public LiteratureInfoModel(Integer pages,
                               Integer words,
                               String title,
                               BigDecimal price,
                               String description,
                               String bookCoverLink,
                               AuthorModel author,
                               GenreModel genre) {
        this.pages = pages;
        this.words = words;
        this.title = title;
        this.price = price;
        this.description = description;
        this.bookCoverLink = bookCoverLink;
        this.author = author;
        this.genre = genre;
    }
}
