package petprojects.bookshop.dbModels.LiteratureInfrastructure.Author;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Data
@Table
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

    @Column(nullable = false,
            length = 100,
            unique = true)
    String fullName;

    @Column
    LocalDate birthDate;

    @Column(nullable = true)
    LocalDate deathDate;

    @Getter(AccessLevel.NONE)
    @Transient
    Integer age;

    @Column
    String pictureLink;

    @Column
    String biographyInShort;

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
    public Integer getAge(){
        return Period.between(birthDate, Objects.requireNonNullElseGet(deathDate, LocalDate::now)).getYears();
    }
}
