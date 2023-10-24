package petprojects.bookshop.models.shopinfrastructure;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Data
@ToString(exclude = "shops")
@Table(name = "cities")
public class CityModel {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(    name = "key_city_id",
            nullable = false,
            unique = true,
            updatable = false)
    private Long cityId;
    @Column(nullable = false,
            length = 500,
            unique = true)
    private String cityName;

    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "city")
    private Set<ShopModel> shops;
    public CityModel(String cityName) {
        this.cityName = cityName;
    }
}
