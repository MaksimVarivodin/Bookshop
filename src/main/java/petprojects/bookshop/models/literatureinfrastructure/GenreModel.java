package petprojects.bookshop.models.literatureinfrastructure;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Data
@Table  (name = "genres")
public class GenreModel {

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE
    )
    @Column(    name = "key_genre_id",
            nullable = false,
            unique = true,
            updatable = false)
    private Long genreId;

    @Column(nullable = false,
            length = 100,
            unique = true)
    private String genreName;
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "genre_id")
    private Set<LiteratureInfoModel> literature;

    public GenreModel(String genreName) {
        this.genreName = genreName;
    }

}
