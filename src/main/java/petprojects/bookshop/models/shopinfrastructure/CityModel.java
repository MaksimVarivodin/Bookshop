package petprojects.bookshop.models.shopinfrastructure;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Data
@Table(name = "cities")
public class CityModel {
    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE
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
    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "city_id")
    private Set<ShopModel> shops;

    public CityModel(String cityName) {
        this.cityName = cityName;
    }
}
