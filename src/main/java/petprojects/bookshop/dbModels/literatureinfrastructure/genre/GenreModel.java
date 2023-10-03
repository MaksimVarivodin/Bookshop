package petprojects.bookshop.dbModels.literatureinfrastructure.genre;

import jakarta.persistence.*;
import lombok.*;
import petprojects.bookshop.dbModels.literatureinfrastructure.author.AuthorModel;
import petprojects.bookshop.dbModels.literatureinfrastructure.literatureinfo.LiteratureInfoModel;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Data
@Table
public class GenreModel {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(nullable = false,
            unique = true,
            updatable = false)
    private Long id;

    @Column(nullable = false,
            length = 100,
            unique = true)
    private String genreName;

    public GenreModel(String genreName) {
        this.genreName = genreName;
    }
    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LiteratureInfoModel> literature = new ArrayList<>();

}
