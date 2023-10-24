package petprojects.bookshop.models.literatureinfrastructure;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Data
@Table(name = "authors")
public class AuthorModel {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "key_author_id",
            nullable = false,
            unique = true,
            updatable = false
    )
    private Long authorId;

    @Column(nullable = false,
            length = 100,
            unique = true)
    private String fullName;

    @Column
    private LocalDate birthDate;

    @Column(nullable = true)
    private LocalDate deathDate;

    @Getter(AccessLevel.NONE)
    @Transient
    private Integer age;

    @Column
    private String pictureLink;

    @Column
    private String biographyInShort;

    public AuthorModel(String fullName,
                       LocalDate birthDate,
                       LocalDate deathDate,
                       String pictureLink,
                       String biographyInShort) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.pictureLink = pictureLink;
        this.biographyInShort = biographyInShort;
    }

    /**
     * Calculate the age based on the birthdate and the current date.
     *
     * @return the age in years
     */
    public Integer getAge() {
        return Period.between(birthDate, Objects.requireNonNullElseGet(deathDate, LocalDate::now)).getYears();
    }
}
