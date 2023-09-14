package petprojects.bookshop.dbModels.LiteratureInfrastructure;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Data
@Table(name = "authors")
public class AuthorModel {
    @Id
    @SequenceGenerator(
            name = "author_sequence",
            sequenceName = "author_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_sequence"
    )
    @Column(nullable = false,
            unique = true,
            updatable = false)
    Long id;

    @Column(nullable = false, length = 100)
    String fullName;

    @Column
    LocalDate birthDate;

    @Column
    String pictureLink;

    @Column
    String biographyInShort;

    public AuthorModel(String fullName,
                       LocalDate birthDate,
                       String pictureLink,
                       String biographyInShort) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.pictureLink = pictureLink;
        this.biographyInShort = biographyInShort;
    }
}
