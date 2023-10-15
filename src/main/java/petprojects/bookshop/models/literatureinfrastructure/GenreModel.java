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
    @GeneratedValue
    @Column(    name = "key_genre_id",
            nullable = false,
            unique = true,
            updatable = false)
    private Long genreId;

    @Column(nullable = false,
            length = 100,
            unique = true)
    private String genreName;

    public GenreModel(String genreName) {
        this.genreName = genreName;
    }

}
