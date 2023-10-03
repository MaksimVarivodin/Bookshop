package petprojects.bookshop.dbModels.literatureinfrastructure.genre;

import jakarta.persistence.*;
import lombok.*;
import petprojects.bookshop.dbModels.literatureinfrastructure.literatureinfo.LiteratureInfoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
            strategy = GenerationType.TABLE
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
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "genre_id")
    private Set<LiteratureInfoModel> literature;

}
